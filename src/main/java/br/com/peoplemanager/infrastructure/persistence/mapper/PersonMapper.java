package br.com.peoplemanager.infrastructure.persistence.mapper;

import br.com.peoplemanager.domain.entity.Person;
import br.com.peoplemanager.domain.entity.dto.PersonDto;
import br.com.peoplemanager.infrastructure.persistence.model.PersonEntity;

public class PersonMapper {
    public static PersonEntity toEntity(Person person) {
        return PersonEntity.builder().fullName(person.getFullName()).birthDate(person.getBirthDate()).build();
    }

    public static Person toDomain(PersonEntity personEntity) {
        return new Person(personEntity.getPersonId(), personEntity.getFullName(), personEntity.getBirthDate());
    }

    public static PersonDto toDto(PersonEntity personEntity) {
        return new PersonDto(personEntity.getPersonId(), personEntity.getFullName(), personEntity.getBirthDate());
    }
}
