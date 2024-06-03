package br.com.peoplemanager.dto.mapper;

import br.com.peoplemanager.domain.model.Address;
import br.com.peoplemanager.domain.enums.StateEnum;
import br.com.peoplemanager.dto.AddressDto;

public class AddressDtoMapper {
    private AddressDtoMapper(){}
    public static Address toModel(AddressDto addressDto) {
        return new Address(addressDto.id(), addressDto.street(), addressDto.zipCode(), addressDto.number(),
                addressDto.city(), StateEnum.fromString(addressDto.state()), addressDto.principal());
    }

    public static AddressDto toDto(Address address) {
        return new AddressDto(address.getAddressId(), address.getStreet(), address.getZipCode(), address.getNumber(),
                address.getCity(), address.getState().getName(), address.getPrincipal());
    }

}
