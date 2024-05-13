package br.com.peoplemanager.util;

import br.com.peoplemanager.dto.address.AddressDto;
import br.com.peoplemanager.dto.person.PersonDto;
import br.com.peoplemanager.entity.Address;
import br.com.peoplemanager.entity.Person;
import br.com.peoplemanager.enuns.StateEnum;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AddressConverter {

    private AddressConverter(){}
    public static Address addressDtoToAddress(AddressDto addressRequest, Person person) {
        return Address.builder()
                .street(addressRequest.getStreet())
                .zipCode(addressRequest.getZipCode())
                .city(addressRequest.getCity())
                .state(StateEnum.fromString(addressRequest.getState()))
                .principal(addressRequest.getPrincipal())
                .number(addressRequest.getNumber())
                .person(person)
                .build();
    }

    public static List<AddressDto> addressesToAddressDto(List<Address> addresses) {
        Function<Address, AddressDto> converter =
                address -> AddressDto.builder()
                        .id(String.valueOf(address.getAddressId()))
                        .principal(address.getPrincipal())
                        .city(address.getCity())
                        .number(address.getNumber())
                        .state(String.valueOf(address.getState().getName()))
                        .street(address.getStreet())
                        .zipCode(address.getZipCode())
                        .build();
        return addresses.stream().map(converter).toList();
    }
}
