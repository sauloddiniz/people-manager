package br.com.peoplemanager.application.usecase.address;

import br.com.peoplemanager.application.gateway.AddressRepositoryGateway;
import br.com.peoplemanager.application.gateway.PersonRepositoryGateway;
import br.com.peoplemanager.domain.entity.Person;
import br.com.peoplemanager.domain.entity.dto.AddressDto;
import br.com.peoplemanager.domain.exception.PersonNotFoundException;

import java.util.List;

public class ListAddress {
    private final AddressRepositoryGateway repository;
    private final PersonRepositoryGateway personRepositoryGateway;

    public ListAddress(AddressRepositoryGateway repository, PersonRepositoryGateway personRepositoryGateway) {
        this.repository = repository;
        this.personRepositoryGateway = personRepositoryGateway;
    }
    public List<AddressDto> execute(Long personId) {
        Person person = getPersonById(personId);
        return repository.findAllByIdPerson(person.getPersonId());
    }

    private Person getPersonById(Long personId) {
        return personRepositoryGateway
                .findById(personId)
                .orElseThrow(() -> new PersonNotFoundException("Person not found: " + personId))
                .toModel();
    }
}
