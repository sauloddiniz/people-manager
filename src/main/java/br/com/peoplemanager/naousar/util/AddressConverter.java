//package br.com.peoplemanager.naousar.util;
//
//import br.com.peoplemanager.domain.entity.dto.AddressDto;
//import br.com.peoplemanager.infrastructure.persistence.model.AddressEntity;
//import br.com.peoplemanager.infrastructure.persistence.model.PersonEntity;
//import br.com.peoplemanager.naousar.enuns.StateEnum;
//
//import java.util.List;
//import java.util.function.Function;
//
//public class AddressConverter {
//
//    private AddressConverter(){}
//    public static AddressEntity addressDtoToAddress(AddressDto addressRequest, PersonEntity person) {
//        return AddressEntity.builder()
//                .street(addressRequest.getStreet())
//                .zipCode(addressRequest.getZipCode())
//                .city(addressRequest.getCity())
//                .state(StateEnum.fromString(addressRequest.getState()))
//                .principal(addressRequest.getPrincipal())
//                .number(addressRequest.getNumber())
//                .person(person)
//                .build();
//    }
//
//    public static List<AddressDto> addressesToAddressDto(List<AddressEntity> addresses) {
//        Function<AddressEntity, AddressDto> converter =
//                address -> AddressDto.builder()
//                        .id(String.valueOf(address.getAddressId()))
//                        .principal(address.getPrincipal())
//                        .city(address.getCity())
//                        .number(address.getNumber())
//                        .state(String.valueOf(address.getState().getName()))
//                        .street(address.getStreet())
//                        .zipCode(address.getZipCode())
//                        .build();
//        return addresses.stream().map(converter).toList();
//    }
//}
