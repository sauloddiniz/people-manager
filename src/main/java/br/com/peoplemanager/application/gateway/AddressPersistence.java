package br.com.peoplemanager.application.gateway;

import br.com.peoplemanager.domain.entity.valueobject.Address;

import java.util.List;

public interface AddressPersistence {
    Address save(Address address);
    void update(Address address);
    Address select(Long addressId);
    List<Address> findAllByIdPerson(Long personId);
    List<Address> findAllPrincipalAddresses();
}
