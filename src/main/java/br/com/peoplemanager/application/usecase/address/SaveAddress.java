package br.com.peoplemanager.application.usecase.address;

import br.com.peoplemanager.application.gateway.AddressRepositoryGateway;
import br.com.peoplemanager.application.usecase.person.GetPerson;
import br.com.peoplemanager.domain.entity.Person;
import br.com.peoplemanager.domain.entity.dto.AddressDto;
import br.com.peoplemanager.domain.entity.valueobject.Address;
import br.com.peoplemanager.domain.enums.StateEnum;

public class SaveAddress {
    private final AddressRepositoryGateway repository;
    private final GetPerson getPerson;
    public SaveAddress(AddressRepositoryGateway repository, GetPerson getPerson) {
        this.repository = repository;
        this.getPerson = getPerson;
    }
    public Long execute(Long personId, AddressDto addressDto) {
        Person person = getPerson.execute(personId).toModel();
        Address address = createAddress(addressDto, person);
        updatePrincipalAddress(person, addressDto.principal());
        return repository.save(address);
    }

    private void updatePrincipalAddress(Person person, Boolean principal) {
        if (principal) {
            person.getAddresses().stream().filter(Address::getPrincipal).forEach(address -> {
                address.setPrincipal(false);
                address.setPerson(person);
                repository.save(address);
            });
        }
    }

    private static Address createAddress(AddressDto addressDto, Person person) {
        return new Address(addressDto.street(), addressDto.zipCode(), addressDto.number(),
                addressDto.city(), StateEnum.fromString(addressDto.state()), addressDto.principal(), person);
    }

}
