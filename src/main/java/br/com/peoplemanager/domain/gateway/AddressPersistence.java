package br.com.peoplemanager.domain.gateway;

import br.com.peoplemanager.domain.model.Address;

import java.util.List;
import java.util.Optional;

public interface AddressPersistence {
    Address save(Address address);
    void update(Address address);
    Optional<Address> findById(Long addressId);
    List<Address> findAllByIdPerson(Long personId);
    List<Address> findAllPrincipalAddresses();
}
