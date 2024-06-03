package br.com.peoplemanager.infrastructure.persistence.gateway;

import br.com.peoplemanager.domain.gateway.AddressPersistence;
import br.com.peoplemanager.domain.model.Address;
import br.com.peoplemanager.infrastructure.persistence.entity.mapper.AddressEntityMapper;
import br.com.peoplemanager.infrastructure.persistence.entity.AddressEntity;
import br.com.peoplemanager.infrastructure.persistence.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressPersistenceImpl implements AddressPersistence {
    private final AddressRepository repository;

    public AddressPersistenceImpl(AddressRepository repository) {
        this.repository = repository;
    }

    @Override
    public Address save(Address address) {
        AddressEntity savedAddress =
                repository.save(AddressEntityMapper.toEntity(address));
        return AddressEntityMapper.toModel(savedAddress);
    }

    @Override
    public Optional<Address> findById(Long addressId) {
        return repository
                .findById(addressId)
                .map(AddressEntityMapper::toModel);
    }

    @Override
    public List<Address> findAllByIdPerson(Long personId) {
        return repository.findAllByPersonPersonId(personId)
                .stream()
                .map(AddressEntityMapper::toModel)
                .toList();
    }

}
