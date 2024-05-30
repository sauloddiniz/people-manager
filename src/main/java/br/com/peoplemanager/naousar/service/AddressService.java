//package br.com.peoplemanager.naousar.service;
//
//import br.com.peoplemanager.domain.entity.dto.AddressDto;
//import br.com.peoplemanager.infrastructure.persistence.model.AddressEntity;
//import br.com.peoplemanager.infrastructure.persistence.model.PersonEntity;
//import br.com.peoplemanager.naousar.enuns.StateEnum;
//import br.com.peoplemanager.naousar.exception.AddressNotFoundException;
//import br.com.peoplemanager.infrastructure.persistence.AddressRepository;
//import br.com.peoplemanager.naousar.util.AddressConverter;
//import org.springframework.stereotype.Service;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Stream;
//
//@Service
//public class AddressService {
//    private final PersonService personService;
//    private final AddressRepository addressRepository;
//
//    public AddressService(PersonService personService, AddressRepository addressRepository) {
//        this.personService = personService;
//        this.addressRepository = addressRepository;
//    }
//
//    public Long saveAddress(AddressDto addressRequest, Long personId) {
//        validPrincipalAddress(addressRequest, personId);
//        AddressEntity address = AddressConverter
//                .addressDtoToAddress(addressRequest,
//                        validExistingPerson(personId));
//        AddressEntity saved = addressRepository.save(address);
//        return saved.getAddressId();
//    }
//
//    public void updateAddress(AddressDto addressRequest, Long personId, Long addressId) {
//        validExistingPerson(personId);
//        validPrincipalAddress(addressRequest, personId);
//        AddressEntity address = validExistingAddress(addressId);
//        setValueAddress(addressRequest, address);
//        addressRepository.save(address);
//    }
//
//    public List<AddressDto> getAddresses(Long personId, String ids) {
//        validExistingPerson(personId);
//        if (isEmpty(ids)) {
//            return AddressConverter.addressesToAddressDto(addressRepository.findAllByPersonPersonId(personId));
//        }
//        return AddressConverter.addressesToAddressDto(addressRepository.findAllByPersonPersonIdAndAddressIdIn(personId, splitIds(ids)));
//    }
//
//    public void updatePrincipalAddress(Long personId, Long addressId) {
//        validExistingPerson(personId);
//        AddressEntity address = validExistingAddress(addressId);
//        List<AddressEntity> addresses = addressRepository.findAllByPersonPersonId(personId);
//        addresses.forEach(adr -> {
//            adr.setPrincipal(adr.equals(address));
//            addressRepository.save(adr);
//        });
//    }
//
//    private void validPrincipalAddress(AddressDto addressRequest, Long personId) {
//        if (addressRequest.getPrincipal()) {
//            changePrincipalAddress(personId);
//        }
//    }
//
//    private void changePrincipalAddress(Long personId) {
//        addressRepository.findAllByPersonPersonId(personId)
//                .stream()
//                .filter(AddressEntity::getPrincipal)
//                .forEach(address -> {
//                    address.setPrincipal(false);
//                    addressRepository.save(address);
//                });
//    }
//
//    private AddressEntity validExistingAddress(Long addressId) {
//        return addressRepository.findById(addressId)
//                .orElseThrow(() -> new AddressNotFoundException("Address not found: " + addressId));
//    }
//
//    private List<Long> splitIds(String ids) {
//        if (hasMultipleNames(ids)) {
//            return Arrays.stream(ids.split(","))
//                    .map(String::trim)
//                    .map(Long::valueOf).toList();
//        }
//        return Stream.of(ids)
//                .map(String::trim)
//                .map(Long::valueOf).toList();
//    }
//
//    private static boolean isEmpty(String names) {
//        return names == null || names.isEmpty();
//    }
//
//    private static boolean hasMultipleNames(String names) {
//        return names != null && names.contains(",");
//    }
//
//    private static void setValueAddress(AddressDto addressRequest, AddressEntity address) {
//        address.setCity(addressRequest.getCity());
//        address.setState(StateEnum.valueOf(addressRequest.getState()));
//        address.setNumber(addressRequest.getNumber());
//        address.setZipCode(addressRequest.getZipCode());
//        address.setStreet(addressRequest.getStreet());
//        address.setPrincipal(addressRequest.getPrincipal());
//    }
//
//    private PersonEntity validExistingPerson(Long personId) {
//        return personService.validPerson(personId);
//    }
//
//}
