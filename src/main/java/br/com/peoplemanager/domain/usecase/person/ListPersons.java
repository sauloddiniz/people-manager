package br.com.peoplemanager.domain.usecase.person;

import br.com.peoplemanager.domain.gateway.PersonPersistence;
import br.com.peoplemanager.domain.entity.Person;

import java.util.List;

public class ListPersons {
    private final PersonPersistence persistence;

    public ListPersons(PersonPersistence repository) {
        this.persistence = repository;
    }

    public List<Person> execute() {
        return persistence.getAllPersons();
    }
}
