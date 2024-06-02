package br.com.peoplemanager.domain.entity.dto;

import br.com.peoplemanager.domain.entity.Person;

import java.time.LocalDate;

public record PersonDto(Long personId, String fullName, LocalDate birthDate, AddressDto principalAddress) {
    public Person toModel(){
        return new Person(personId, fullName, birthDate);
    };

    public static PersonDto fromModel(Person person) {
        return new PersonDto(person.getPersonId(), person.getFullName(), person.getBirthDate(),
                person.getPrincipalAddress() != null
                        ? AddressDto.fromModel(person.getPrincipalAddress())
                        : null
                );
    }
}
