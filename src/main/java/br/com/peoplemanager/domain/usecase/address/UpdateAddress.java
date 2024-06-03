package br.com.peoplemanager.domain.usecase.address;
import br.com.peoplemanager.domain.model.Address;

public class UpdateAddress {
    private final GetAddress getAddress;
    private final SaveAddress saveAddress;

    public UpdateAddress(GetAddress getAddress, SaveAddress saveAddress) {
        this.getAddress = getAddress;
        this.saveAddress = saveAddress;
    }

    public Address execute(Long personId, Long addressId, Address address) {
        Address addressSave = getAddress.execute(personId, addressId);
        address.setAddressId(addressSave.getAddressId());
        return saveAddress.execute(personId, address);
    }
}
