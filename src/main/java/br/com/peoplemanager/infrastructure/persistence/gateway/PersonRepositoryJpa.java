package br.com.peoplemanager.infrastructure.persistence.gateway;

import br.com.peoplemanager.application.gateway.PersonRepositoryGateway;
import br.com.peoplemanager.domain.entity.Person;
import br.com.peoplemanager.domain.entity.dto.PersonDto;
import br.com.peoplemanager.infrastructure.persistence.PersonRepository;
import br.com.peoplemanager.infrastructure.persistence.model.PersonEntity;
import br.com.peoplemanager.infrastructure.persistence.mapper.PersonMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class PersonRepositoryJpa implements PersonRepositoryGateway {
    private final PersonRepository repository;
    public PersonRepositoryJpa(PersonRepository repository) {
        this.repository = repository;
    }
    @Override
    public Person save(Person person) {
        PersonEntity personEntity = PersonMapper.toEntity(person);
        PersonEntity savedPerson = repository.save(personEntity);
        return PersonMapper.toDomain(savedPerson);
    }
    @Override
    public List<PersonDto> getAllPersons() {
        return repository.findAll().stream().map(PersonMapper::toDto).toList();
    }
    @Override
    public List<PersonDto> filterPerson(List<String> names) {
        return repository.findAll(where(PersonRepository.containsHasName(names))).stream().map(PersonMapper::toDto).toList();
    }
    @Override
    public Optional<PersonDto> findById(Long personId) {
        return repository.findById(personId).map(PersonMapper::toDto);
    }

}
