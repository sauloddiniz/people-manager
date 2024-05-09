package br.com.peoplemanager.service;

import br.com.peoplemanager.dto.person.PersonDto;
import br.com.peoplemanager.entity.Person;
import br.com.peoplemanager.exception.PersonNotFoundException;
import br.com.peoplemanager.repository.PersonRepository;
import br.com.peoplemanager.util.DateConverter;
import br.com.peoplemanager.util.PersonConverter;
import org.springframework.stereotype.Service;

import java.util.*;

import static br.com.peoplemanager.repository.PersonRepository.containsHasName;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Long savePerson(final PersonDto personRequestDto) {
        final Person saved =
                personRepository.save(PersonConverter.personDtoToPerson(personRequestDto));
        return saved.getPersonId();
    }

    public void updatePerson(PersonDto personRequestDto, Long personId) {
        Person person = validPerson(personId);
        person.setFullName(personRequestDto.getFullName());
        person.setBirthDate(DateConverter.converterStringToLocalDate(personRequestDto.getBirthDate()));
        personRepository.save(person);
    }

    public Person validPerson(Long personId) {
        return personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException("Person not found: " + personId));
    }

    public List<PersonDto> listPerson(String names) {
        if (isEmpty(names)) {
            return PersonConverter.personsToPersonsDto(personRepository.findAll());
        }
        return PersonConverter.personsToPersonsDto(personRepository
                .findAll(where(containsHasName(splitNamesToIndividualPersons(names)))));
    }

    private List<String> splitNamesToIndividualPersons(String names) {
        if (hasMultipleNames(names)) {
            return Arrays.asList(names.split(","));
        }
        return Collections.singletonList(names);
    }

    private static boolean isEmpty(String names) {
        return names == null || names.isEmpty();
    }

    private static boolean hasMultipleNames(String names) {
        return names != null && names.contains(",");
    }

}
