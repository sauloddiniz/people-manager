package br.com.peoplemanager.infrastructure.persistence.gateway;

import br.com.peoplemanager.application.gateway.AddressRepositoryGateway;
import br.com.peoplemanager.domain.entity.dto.AddressDto;
import br.com.peoplemanager.domain.entity.valueobject.Address;
import br.com.peoplemanager.infrastructure.persistence.AddressRepository;
import br.com.peoplemanager.infrastructure.persistence.mapper.AddressMapper;
import br.com.peoplemanager.infrastructure.persistence.model.AddressEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressRepositoryJpa implements AddressRepositoryGateway {

    private final AddressRepository repository;
    public AddressRepositoryJpa(AddressRepository repository) {
        this.repository = repository;
    }
    @Override
    public Long save(Address address) {
        AddressEntity savedAddress = repository.save(AddressMapper.toEntity(address));
        return savedAddress.getAddressId();
    }
    @Override
    public List<AddressDto> findAllByIdPerson(Long personId) {
        return repository.findAllByPersonPersonId(personId)
                .stream()
                .map(AddressMapper::toModel)
                .toList();
    }
}
