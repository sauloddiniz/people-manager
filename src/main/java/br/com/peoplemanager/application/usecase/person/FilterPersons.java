package br.com.peoplemanager.application.usecase.person;

import br.com.peoplemanager.application.gateway.PersonPersistence;
import br.com.peoplemanager.domain.entity.Person;

import java.util.Arrays;
import java.util.List;

public class FilterPersons {
    private final PersonPersistence persistence;
    public FilterPersons(PersonPersistence repository) {
        this.persistence = repository;
    }
    public List<Person> execute(String names) {
        List<String> list = Arrays.stream(names.split(",")).toList();
        return persistence.filterPerson(list);
    }
}
