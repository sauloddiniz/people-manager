package br.com.peoplemanager.service;

import br.com.peoplemanager.dto.address.AddressDto;
import br.com.peoplemanager.entity.Address;
import br.com.peoplemanager.entity.Person;
import br.com.peoplemanager.exception.AddressNotFoundException;
import br.com.peoplemanager.repository.AddressRepository;
import br.com.peoplemanager.util.AddressConverter;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Service
public class AddressService {
    private final PersonService personService;
    private final AddressRepository addressRepository;

    public AddressService(PersonService personService, AddressRepository addressRepository) {
        this.personService = personService;
        this.addressRepository = addressRepository;
    }

    public Long saveAddress(AddressDto addressRequest, Long personId) {
        validPrincipalAddress(addressRequest, personId);
        Address address = AddressConverter
                .addressDtoToAddress(addressRequest,
                        validExistingPerson(personId));
        Address saved = addressRepository.save(address);
        return saved.getAddressId();
    }

    public void updateAddress(AddressDto addressRequest, Long personId, Long addressId) {
        validExistingPerson(personId);
        validPrincipalAddress(addressRequest, personId);
        Address address = validExistingAddress(addressId);
        setValueAddress(addressRequest, address);
        addressRepository.save(address);
    }

    public List<AddressDto> getAddresses(Long personId, String ids) {
        validExistingPerson(personId);
        if (isEmpty(ids)) {
            return AddressConverter.addressesToAddressDto(addressRepository.findAllByPersonPersonId(personId));
        }
        return AddressConverter.addressesToAddressDto(addressRepository.findAllByPersonPersonIdAndAddressIdIn(personId, splitIds(ids)));
    }

    public void updatePrincipalAddress(Long personId, Long addressId) {
        validExistingPerson(personId);
        Address address = validExistingAddress(addressId);
        List<Address> addresses = addressRepository.findAllByPersonPersonId(personId);
        addresses.forEach(adr -> {
            adr.setPrincipal(adr.equals(address));
            addressRepository.save(adr);
        });
    }

    private void validPrincipalAddress(AddressDto addressRequest, Long personId) {
        if (addressRequest.getPrincipal()) {
            changePrincipalAddress(personId);
        }
    }

    private void changePrincipalAddress(Long personId) {
        addressRepository.findAllByPersonPersonId(personId)
                .stream()
                .filter(Address::getPrincipal)
                .forEach(address -> {
                    address.setPrincipal(false);
                    addressRepository.save(address);
                });
    }

    private Address validExistingAddress(Long addressId) {
        return addressRepository.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundException("Address not found: " + addressId));
    }

    private List<Long> splitIds(String ids) {
        if (hasMultipleNames(ids)) {
            return Arrays.stream(ids.split(","))
                    .map(Long::valueOf).toList();
        }
        return Stream.of(ids).map(Long::valueOf).toList();
    }

    private static boolean isEmpty(String names) {
        return names == null || names.isEmpty();
    }

    private static boolean hasMultipleNames(String names) {
        return names != null && names.contains(",");
    }

    private static void setValueAddress(AddressDto addressRequest, Address address) {
        address.setCity(addressRequest.getCity());
        address.setState(addressRequest.getState());
        address.setNumber(addressRequest.getNumber());
        address.setZipCode(addressRequest.getZipCode());
        address.setStreet(addressRequest.getStreet());
        address.setPrincipal(addressRequest.getPrincipal());
    }

    private Person validExistingPerson(Long personId) {
        return personService.validPerson(personId);
    }

}
