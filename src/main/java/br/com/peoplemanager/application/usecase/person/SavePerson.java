package br.com.peoplemanager.application.usecase.person;

import br.com.peoplemanager.application.gateway.PersonPersistence;
import br.com.peoplemanager.domain.entity.Person;
import br.com.peoplemanager.domain.entity.dto.PersonDto;

public class SavePerson {
    private final PersonPersistence persistence;

    public SavePerson(PersonPersistence repository) {
        this.persistence = repository;
    }

    public Person execute(final PersonDto person) {
        Person newPerson = new Person(person.fullName(), person.birthDate());
        return persistence.save(newPerson);
    }

    public Person execute(final Person person) {
        return persistence.save(person);
    }
}
