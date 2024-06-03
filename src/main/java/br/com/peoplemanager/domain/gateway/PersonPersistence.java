package br.com.peoplemanager.domain.gateway;

import br.com.peoplemanager.domain.entity.Person;
import br.com.peoplemanager.domain.entity.dto.PersonDto;

import java.util.List;
import java.util.Optional;

public interface PersonPersistence {
    Person save(Person person);
    List<Person> getAllPersons();
    List<Person> filterPerson(List<String> names);
    Optional<Person> findById(Long personId);
}
