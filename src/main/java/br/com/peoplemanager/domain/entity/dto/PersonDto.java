package br.com.peoplemanager.domain.entity.dto;

import br.com.peoplemanager.domain.entity.Person;

import java.time.LocalDate;
import java.util.List;

public record PersonDto(Long personId, String fullName, LocalDate birthDate, List<AddressDto> addressDtoList) {
    public Person toModel(){
        return new Person(personId, fullName, birthDate,
                addressDtoList.stream().map(AddressDto::toModel).toList());
    };
}
