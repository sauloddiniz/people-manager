package br.com.peoplemanager.application.usecase.person;

import br.com.peoplemanager.application.gateway.PersonPersistence;
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
