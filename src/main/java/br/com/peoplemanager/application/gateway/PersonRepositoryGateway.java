package br.com.peoplemanager.application.gateway;

import br.com.peoplemanager.domain.entity.Person;
import br.com.peoplemanager.domain.entity.dto.PersonDto;

import java.util.List;
import java.util.Optional;

public interface PersonRepositoryGateway {
    Person save(Person person);
    List<PersonDto> getAllPersons();
    List<PersonDto> filterPerson(List<String> names);
    Optional<PersonDto> findById(Long personId);
}
