package br.com.peoplemanager.infrastructure.persistence;

import br.com.peoplemanager.infrastructure.persistence.model.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
    List<AddressEntity> findAllByPersonPersonId(Long id);
    List<AddressEntity> findAllByPersonPersonIdAndAddressIdIn(Long personId, Collection<Long> addressId);

}
