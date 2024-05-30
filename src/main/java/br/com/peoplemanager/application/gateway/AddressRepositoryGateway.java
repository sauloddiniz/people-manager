package br.com.peoplemanager.application.gateway;

import br.com.peoplemanager.domain.entity.dto.AddressDto;
import br.com.peoplemanager.domain.entity.valueobject.Address;

import java.util.List;

public interface AddressRepositoryGateway {
    Long save(Address address);
    List<AddressDto> findAllByIdPerson(Long personId);
}
