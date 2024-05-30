package br.com.peoplemanager.application.usecase.person;

import br.com.peoplemanager.application.gateway.PersonRepositoryGateway;
import br.com.peoplemanager.domain.entity.dto.PersonDto;
import br.com.peoplemanager.domain.exception.PersonNotFoundException;

public class GetPerson {
    private final PersonRepositoryGateway repository;
    public GetPerson(PersonRepositoryGateway repository) {
        this.repository = repository;
    }
    public PersonDto execute(Long personId) {
        return repository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException("Person not found: " + personId));
    }
}
