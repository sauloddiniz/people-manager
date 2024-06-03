package br.com.peoplemanager.domain.usecase.address;

import br.com.peoplemanager.domain.gateway.AddressPersistence;
import br.com.peoplemanager.domain.entity.valueobject.Address;

import java.util.List;

public class ListAddressByIdPerson {
    private final AddressPersistence persistence;
    public ListAddressByIdPerson(AddressPersistence persistence) {
        this.persistence = persistence;
    }
    public List<Address> execute(Long personId) {
        return persistence.findAllByIdPerson(personId);
    }
}
