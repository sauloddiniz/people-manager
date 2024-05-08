package br.com.peoplemanager.service;

import br.com.peoplemanager.dto.person.PersonRequestDto;
import br.com.peoplemanager.dto.person.PersonResponseDto;
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

    ;

    public Long savePerson(final PersonRequestDto personRequestDto) {
        final Person saved =
                personRepository.save(PersonConverter.personRequestDtoToPerson(personRequestDto));
        return saved.getPersonId();
    }

    public void updatePerson(PersonRequestDto personRequestDto, Long personId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException("Person not found: " + personId));
        person.setFullName(personRequestDto.getFullName());
        person.setBirthDate(DateConverter.converterStringToLocalDate(personRequestDto.getBirthDate()));
        personRepository.save(person);
    }

    public List<PersonResponseDto> listPerson(String names) {
        if (isDefaultValue(names)) {
            return PersonConverter.personsToPersonsResponseDto(personRepository.findAll());
        }
        return PersonConverter.personsToPersonsResponseDto(personRepository
                .findAll(where(containsHasName(verifyAsListValuePerson(names)))));
    }

    private List<String> verifyAsListValuePerson(String persons) {
        if (persons.contains(",")) {
            return Arrays.asList(persons.split(","));
        } else if (isContainOneValue(persons)) {
            return List.of(persons);
        }
        return new ArrayList<>();
    }

    private static boolean isContainOneValue(String persons) {
        return persons != null && !persons.contains(",");
    }

    private static boolean isDefaultValue(String names) {
        return names != null && names.equals("");
    }

}
