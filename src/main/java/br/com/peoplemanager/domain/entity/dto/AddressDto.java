package br.com.peoplemanager.domain.entity.dto;

import br.com.peoplemanager.domain.entity.valueobject.Address;
import br.com.peoplemanager.domain.enums.StateEnum;

public record AddressDto(Long id, String street, String zipCode, String number, String city, String state,
                         Boolean principal) {
    public Address toModel() {
        return new Address(id, street, zipCode, number, city, StateEnum.fromString(state), principal);
    }
}
