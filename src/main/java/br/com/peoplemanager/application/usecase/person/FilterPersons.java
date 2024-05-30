package br.com.peoplemanager.application.usecase.person;

import br.com.peoplemanager.application.gateway.PersonRepositoryGateway;
import br.com.peoplemanager.domain.entity.dto.PersonDto;

import java.util.Arrays;
import java.util.List;

public class FilterPersons {
    private final PersonRepositoryGateway repository;
    public FilterPersons(PersonRepositoryGateway repository) {
        this.repository = repository;
    }
    public List<PersonDto> execute(String names) {
        List<String> list = Arrays.stream(names.split(",")).toList();
        return repository.filterPerson(list);
    }
}
