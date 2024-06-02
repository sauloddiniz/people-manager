package br.com.peoplemanager.infrastructure.persistence.gateway;

import br.com.peoplemanager.application.gateway.PersonPersistence;
import br.com.peoplemanager.domain.entity.Person;
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
        PersonEntity personEntity = new PersonEntity(person);
        PersonEntity savedPerson = repository.save(personEntity);
        return savedPerson.toModel();
    }
    @Override
    public List<Person> getAllPersons() {
        return repository
                .findAll()
                .stream()
                .map(PersonEntity::toModel)
                .toList();
    }
    @Override
    public List<Person> filterPerson(List<String> names) {
        return repository
                .findAll(where(br.com.peoplemanager.infrastructure.persistence.repository.PersonRepository
                        .containsHasName(names)))
                .stream()
                .map(PersonEntity::toModel)
                .toList();
    }
    @Override
    public Optional<Person> findById(Long personId) {
        return repository
                .findById(personId)
                .map(PersonEntity::toModel);
    }

}
