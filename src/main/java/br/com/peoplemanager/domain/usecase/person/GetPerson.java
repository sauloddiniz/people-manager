package br.com.peoplemanager.domain.usecase.person;

import br.com.peoplemanager.domain.gateway.PersonPersistence;
import br.com.peoplemanager.domain.entity.Person;
import br.com.peoplemanager.domain.exception.PersonNotFoundException;

public class GetPerson {
    private final PersonPersistence persistence;
    public GetPerson(PersonPersistence repository) {
        this.persistence = repository;
    }
    public Person execute(Long personId) {
        return persistence.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException("Person not found: " + personId));
    }
}
