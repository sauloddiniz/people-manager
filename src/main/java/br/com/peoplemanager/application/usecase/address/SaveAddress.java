package br.com.peoplemanager.application.usecase.address;

import br.com.peoplemanager.application.gateway.AddressRepositoryGateway;
import br.com.peoplemanager.application.gateway.PersonRepositoryGateway;
import br.com.peoplemanager.domain.entity.Person;
import br.com.peoplemanager.domain.entity.dto.AddressDto;
import br.com.peoplemanager.domain.entity.valueobject.Address;
import br.com.peoplemanager.domain.enums.StateEnum;
import br.com.peoplemanager.domain.exception.PersonNotFoundException;

public class SaveAddress {
    private final AddressRepositoryGateway repository;
    private final PersonRepositoryGateway personRepositoryGateway;
    public SaveAddress(AddressRepositoryGateway repository, PersonRepositoryGateway personRepositoryGateway) {
        this.repository = repository;
        this.personRepositoryGateway = personRepositoryGateway;
    }
    public Long execute(Long personId, AddressDto addressDto) {
        Person person = getPersonById(personId);
        Address address = createAddress(addressDto, person);
        return repository.save(address);
    }

    private static Address createAddress(AddressDto addressDto, Person person) {
        return new Address(addressDto.street(), addressDto.zipCode(), addressDto.number(),
                addressDto.city(), StateEnum.fromString(addressDto.state()), addressDto.principal(), person);
    }
    private Person getPersonById(Long personId) {
        return personRepositoryGateway
                .findById(personId)
                .orElseThrow(() -> new PersonNotFoundException("Person not found: " + personId))
                .toModel();
    }
}
