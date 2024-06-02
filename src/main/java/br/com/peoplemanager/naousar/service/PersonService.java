//package br.com.peoplemanager.naousar.service;
//
//import br.com.peoplemanager.infrastructure.controller.dto.PersonDto;
//import br.com.peoplemanager.infrastructure.persistence.model.PersonEntity;
//import br.com.peoplemanager.naousar.exception.PersonNotFoundException;
//import br.com.peoplemanager.infrastructure.persistence.repository.PersonRepository;
//import br.com.peoplemanager.naousar.util.DateConverter;
//import br.com.peoplemanager.naousar.util.PersonConverter;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//
//import static org.springframework.data.jpa.domain.Specification.where;
//
//@Service
//public class PersonService {
//
//    private final PersonRepository personRepository;
//
//    public PersonService(final PersonRepository personRepository) {
//        this.personRepository = personRepository;
//    }
//
//    public Long savePerson(final PersonDto personRequestDto) {
//        final PersonEntity saved =
//                personRepository.save(PersonConverter.personDtoToPerson(personRequestDto));
//        return saved.getPersonId();
//    }
//
//    public void updatePerson(PersonDto personRequestDto, Long personId) {
//        PersonEntity person = validPerson(personId);
//        person.setFullName(personRequestDto.getFullName());
//        person.setBirthDate(DateConverter.converterStringToLocalDate(personRequestDto.getBirthDate()));
//        personRepository.save(person);
//    }
//
//    public PersonEntity validPerson(Long personId) {
//        return personRepository.findById(personId)
//                .orElseThrow(() -> new PersonNotFoundException("Person not found: " + personId));
//    }
//
//    public List<PersonDto> listPerson(String names) {
//        if (isEmpty(names)) {
//            return PersonConverter.personsToPersonsDto(personRepository.findAll());
//        }
//        return PersonConverter.personsToPersonsDto(personRepository
//                .findAll(where(PersonRepository.containsHasName(splitNamesToIndividualPersons(names)))));
//    }
//
//    private List<String> splitNamesToIndividualPersons(String names) {
//        if (hasMultipleNames(names)) {
//            return Arrays.asList(names.split(","));
//        }
//        return Collections.singletonList(names);
//    }
//
//    private static boolean isEmpty(String names) {
//        return names == null || names.isEmpty();
//    }
//
//    private static boolean hasMultipleNames(String names) {
//        return names != null && names.contains(",");
//    }
//
//}
