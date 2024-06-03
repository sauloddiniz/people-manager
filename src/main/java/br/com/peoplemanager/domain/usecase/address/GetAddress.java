package br.com.peoplemanager.domain.usecase.address;

import br.com.peoplemanager.domain.model.Address;
import br.com.peoplemanager.domain.exception.AddressNotFoundException;
import br.com.peoplemanager.domain.gateway.AddressPersistence;

public class GetAddress {
    private final AddressPersistence persistence;
    public GetAddress(AddressPersistence persistence) {
        this.persistence = persistence;
    }

    public Address execute(Long personId, Long addressId) {
        Address address = persistence.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundException("Address not found: " + addressId));
        if (validAddressPersonIdEqualPersonId(personId, address)) {
            throw new AddressNotFoundException("Address not found: " + addressId);
        }
        return address;
    }

    private static boolean validAddressPersonIdEqualPersonId(Long personId, Address address) {
        return address.getPerson() != null && !address.getPerson().getPersonId().equals(personId);
    }
}
