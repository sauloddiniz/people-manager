package br.com.peoplemanager.infrastructure.persistence.entity.mapper;

import br.com.peoplemanager.domain.model.Person;
import br.com.peoplemanager.infrastructure.persistence.entity.PersonEntity;

import java.util.Collection;
import java.util.Optional;

public class PersonEntityMapper {

    private PersonEntityMapper(){}
    public static PersonEntity toEntity(Person person) {
        return PersonEntity.builder()
                .personId(person.getPersonId())
                .fullName(person.getFullName())
                .birthDate(person.getBirthDate())
                .build();
    }
    public static Person toModel(PersonEntity entity) {
        return new Person(entity.getPersonId(), entity.getFullName(), entity.getBirthDate(),
                Optional.ofNullable(entity.getAddresses())
                        .stream()
                        .flatMap(Collection::stream)
                        .map(AddressEntityMapper::toModel)
                        .toList());

    }

    public static Person toModelNotContainsAddress(PersonEntity entity) {
        return new Person(entity.getPersonId(), entity.getFullName(), entity.getBirthDate());
    }

}
