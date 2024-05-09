package br.com.peoplemanager.repository;

import br.com.peoplemanager.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findAllByPersonPersonId(Long id);
    List<Address> findAllByPersonPersonIdAndAddressIdIn(Long personId, Collection<Long> addressId);

}
