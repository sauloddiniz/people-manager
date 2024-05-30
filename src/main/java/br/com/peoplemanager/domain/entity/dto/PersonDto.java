package br.com.peoplemanager.domain.entity.dto;

import br.com.peoplemanager.domain.entity.Person;

import java.time.LocalDate;

public record PersonDto(Long personId, String fullName, LocalDate birthDate) {
    public Person toModel(){
        return new Person(personId, fullName, birthDate);
    };
}
