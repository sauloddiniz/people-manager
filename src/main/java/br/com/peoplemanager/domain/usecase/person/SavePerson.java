package br.com.peoplemanager.domain.usecase.person;

import br.com.peoplemanager.domain.gateway.PersonPersistence;
import br.com.peoplemanager.domain.entity.Person;

public class SavePerson {
    private final PersonPersistence persistence;

    public SavePerson(PersonPersistence repository) {
        this.persistence = repository;
    }

    public Person execute(final Person person) {
        Person createPerson =
                new Person(person.getPersonId(), person.getFullName(), person.getBirthDate());
        return persistence.save(createPerson);
    }
}
