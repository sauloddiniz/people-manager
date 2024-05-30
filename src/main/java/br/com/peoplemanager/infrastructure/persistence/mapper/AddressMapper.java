package br.com.peoplemanager.infrastructure.persistence.mapper;

import br.com.peoplemanager.domain.entity.dto.AddressDto;
import br.com.peoplemanager.domain.entity.valueobject.Address;
import br.com.peoplemanager.domain.enums.StateEnum;
import br.com.peoplemanager.infrastructure.persistence.model.AddressEntity;
import br.com.peoplemanager.infrastructure.persistence.model.PersonEntity;

public class AddressMapper {
    public static AddressEntity toEntity(Address address) {
        return AddressEntity.builder()
                    .city(address.getCity())
                    .number(address.getNumber())
                    .principal(address.getPrincipal())
                    .street(address.getStreet())
                    .zipCode(address.getZipCode())
                    .addressId(address.getAddressId())
                    .state(StateEnum.fromString(address.getState().toString()))
                    .person(PersonEntity.builder()
                                .personId(address.getPerson().getPersonId())
                            .build())
                .build();
    }

    public static AddressDto toModel(AddressEntity address) {
        return new AddressDto(address.getAddressId(), address.getStreet(), address.getZipCode(), address.getNumber(),
                address.getCity(), address.getState().toString(), address.getPrincipal());
    }
}
