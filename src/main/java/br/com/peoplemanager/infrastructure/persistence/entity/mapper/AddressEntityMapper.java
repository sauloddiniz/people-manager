package br.com.peoplemanager.infrastructure.persistence.entity.mapper;

import br.com.peoplemanager.domain.model.Address;
import br.com.peoplemanager.infrastructure.persistence.entity.AddressEntity;

public class AddressEntityMapper {

    public static AddressEntity toEntity(Address address) {
        return AddressEntity.builder()
                .addressId(address.getAddressId())
                .street(address.getStreet())
                .zipCode(address.getZipCode())
                .number(address.getNumber())
                .city(address.getCity())
                .state(address.getState())
                .principal(address.getPrincipal())
                .person(PersonEntityMapper.toEntity(address.getPerson()))
                .build();
    }

    public static Address toModel(AddressEntity entity) {
        return new Address(entity.getAddressId(), entity.getStreet(), entity.getZipCode(),
                entity.getNumber(), entity.getCity(), entity.getState(), entity.getPrincipal(),
                PersonEntityMapper.toModelNotContainsAddress(entity.getPerson()));
    }

}
