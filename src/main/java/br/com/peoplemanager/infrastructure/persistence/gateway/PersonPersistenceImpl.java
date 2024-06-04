package br.com.peoplemanager.infrastructure.persistence.gateway;

import br.com.peoplemanager.domain.gateway.PersonPersistence;
import br.com.peoplemanager.domain.model.Person;
import br.com.peoplemanager.infrastructure.persistence.entity.mapper.PersonEntityMapper;
import br.com.peoplemanager.infrastructure.persistence.entity.PersonEntity;
import br.com.peoplemanager.infrastructure.persistence.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class PersonPersistenceImpl implements PersonPersistence {
    private final PersonRepository repository;

    public PersonPersistenceImpl(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public Person save(Person person) {
        PersonEntity savedPerson = repository
                .save(PersonEntityMapper.toEntity(person));
        return PersonEntityMapper.toModel(savedPerson);
    }

    @Override
    public List<Person> filterPerson(List<String> names) {
        return repository
                .findAll(where(PersonRepository
                        .containsHasName(names)))
                .stream()
                .map(PersonEntityMapper::toModel)
                .toList();
    }

    @Override
    public Optional<Person> findById(Long personId) {
        return repository
                .findById(personId)
                .map(PersonEntityMapper::toModel);
    }

}
