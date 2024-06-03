package br.com.peoplemanager.domain.usecase.address;

import br.com.peoplemanager.domain.model.Address;

public class UpdatePrincipalAddress {

    private final GetAddress getAddress;

    private final SaveAddress saveAddress;

    public UpdatePrincipalAddress(GetAddress getAddress, SaveAddress saveAddress) {
        this.getAddress = getAddress;
        this.saveAddress = saveAddress;
    }

    public Address execute(Long personId, Long addressId, Boolean principal) {
        Address address = getAddress.execute(personId, addressId);
        address.setPrincipal(principal);
        return saveAddress.execute(personId, address);
    }
}
