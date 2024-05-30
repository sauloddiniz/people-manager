package br.com.peoplemanager.application.usecase.person;

import br.com.peoplemanager.application.gateway.PersonRepositoryGateway;
import br.com.peoplemanager.domain.entity.dto.PersonDto;

import java.util.List;

public class ListPersons {
    private final PersonRepositoryGateway repository;

    public ListPersons(PersonRepositoryGateway repository) {
        this.repository = repository;
    }
    public List<PersonDto> execute() {
        return repository.getAllPersons();
    }
}
