package br.com.peoplemanager.infrastructure.persistence.repository;

import br.com.peoplemanager.infrastructure.persistence.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
    List<AddressEntity> findAllByPersonPersonId(Long id);
}
