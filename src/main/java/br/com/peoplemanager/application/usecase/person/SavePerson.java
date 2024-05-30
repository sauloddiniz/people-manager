package br.com.peoplemanager.application.usecase.person;

import br.com.peoplemanager.application.gateway.PersonRepositoryGateway;
import br.com.peoplemanager.domain.entity.Person;
import br.com.peoplemanager.domain.entity.dto.PersonDto;

public class SavePerson {
    private final PersonRepositoryGateway repository;
    public SavePerson(PersonRepositoryGateway repository) {
        this.repository = repository;
    }

    public Person execute(final PersonDto person) {
        Person newPerson = new Person(person.fullName(), person.birthDate());
        return repository.save(newPerson);
    }
    public Person execute(final Person person) {
        return repository.save(person);
    }
}
