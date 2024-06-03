package br.com.peoplemanager.infrastructure.persistence.gateway;

import br.com.peoplemanager.domain.gateway.AddressPersistence;
import br.com.peoplemanager.domain.entity.valueobject.Address;
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
        AddressEntity savedAddress = repository.save(new AddressEntity(address));
        return savedAddress.toModel();
    }
    @Override
    public void update(Address address) {
        repository.save(new AddressEntity(address));
    }
    @Override
    public Optional<Address> findById(Long addressId) {
        return repository.findById(addressId).map(AddressEntity::toModel);
    }

    @Override
    public List<Address> findAllByIdPerson(Long personId) {
        return repository.findAllByPersonPersonId(personId)
                .stream()
                .map(AddressEntity::toModel)
                .toList();
    }

    @Override
    public List<Address> findAllPrincipalAddresses() {
        return null;
    }
}
