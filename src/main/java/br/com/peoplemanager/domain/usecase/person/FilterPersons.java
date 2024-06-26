package br.com.peoplemanager.domain.usecase.person;

import br.com.peoplemanager.domain.gateway.PersonPersistence;
import br.com.peoplemanager.domain.model.Person;

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
