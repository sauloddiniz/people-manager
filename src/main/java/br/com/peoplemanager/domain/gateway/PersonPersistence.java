package br.com.peoplemanager.domain.gateway;

import br.com.peoplemanager.domain.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonPersistence {
    Person save(Person person);
    List<Person> filterPerson(List<String> names);
    Optional<Person> findById(Long personId);

}
