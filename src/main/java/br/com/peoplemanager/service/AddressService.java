package br.com.peoplemanager.service;

import br.com.peoplemanager.dto.address.AddressDto;
import br.com.peoplemanager.entity.Address;
import br.com.peoplemanager.entity.Person;
import br.com.peoplemanager.exception.AddressNotFoundException;
import br.com.peoplemanager.repository.AddressRepository;
import br.com.peoplemanager.util.AddressConverter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AddressService {
    private final PersonService personService;
    private final AddressRepository addressRepository;

    public AddressService(PersonService personService, AddressRepository addressRepository) {
        this.personService = personService;
        this.addressRepository = addressRepository;
    }

    public Long saveAddress(AddressDto addressRequest, Long personId) {
        Person person = validExistingPerson(personId);
        validPrincipalAddress(addressRequest, personId);
        Address address = AddressConverter
                .addressDtoToAddress(addressRequest, person);
        Address saved = addressRepository.save(address);
        return saved.getAddressId();
    }

    private Person validExistingPerson(Long personId) {
        return personService.validPerson(personId);
    }

    public void updateAddress(AddressDto addressRequest, Long personId, Long addressId) {
        validExistingPerson(personId);
        validPrincipalAddress(addressRequest, personId);
        Address address = validExistingAddress(addressId);
        setValueAddress(addressRequest, address);
        addressRepository.save(address);
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

    public List<AddressDto> getAddresses(Long personId, String ids) {
        validExistingPerson(personId);
        if (isDefaultValue(ids)) {
            return AddressConverter.addressesToAddressDto(addressRepository.findAllByPersonPersonId(personId));
        }
        return AddressConverter.addressesToAddressDto(addressRepository.findAllByPersonPersonIdAndAddressIdIn(personId, verifyAsListValue(ids)));
    }

    private Address validExistingAddress(Long addressId) {
        return addressRepository.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundException("Address not found: " + addressId));
    }

    private List<Long> verifyAsListValue(String ids) {
        if (ids.contains(",")) {
            return Arrays.stream(ids.split(","))
                    .map(Long::valueOf).toList();
        } else if (isContainOneValue(ids)) {
            return List.of(Long.valueOf(ids));
        }
        return new ArrayList<>();
    }

    private static boolean isContainOneValue(String persons) {
        return persons != null && !persons.contains(",");
    }

    private static boolean isDefaultValue(String names) {
        return names != null && names.isEmpty();
    }

    private static void setValueAddress(AddressDto addressRequest, Address address) {
        address.setCity(addressRequest.getCity());
        address.setState(addressRequest.getState());
        address.setNumber(addressRequest.getNumber());
        address.setZipCode(addressRequest.getZipCode());
        address.setStreet(addressRequest.getStreet());
        address.setPrincipal(addressRequest.getPrincipal());
    }


}
