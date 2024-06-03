package br.com.peoplemanager.dto.mapper;

import br.com.peoplemanager.domain.model.Person;
import br.com.peoplemanager.dto.PersonDto;

public class PersonDtoMapper {
    private PersonDtoMapper(){}
    public static Person toModel(PersonDto personDto){
        return new Person(personDto.personId(), personDto.fullName(), personDto.birthDate());
    }

    public static PersonDto toDto(Person person) {
        return new PersonDto(person.getPersonId(), person.getFullName(), person.getBirthDate(),
                person.getPrincipalAddress() != null
                        ? AddressDtoMapper.toDto(person.getPrincipalAddress())
                        : null
        );
    }
}
