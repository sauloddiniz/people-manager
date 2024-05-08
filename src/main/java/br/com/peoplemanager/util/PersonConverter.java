package br.com.peoplemanager.util;

import br.com.peoplemanager.dto.person.PersonRequestDto;
import br.com.peoplemanager.dto.person.PersonResponseDto;
import br.com.peoplemanager.entity.Person;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PersonConverter {

    public static Person personRequestDtoToPerson(PersonRequestDto personRequestDto) {
        return Person.builder()
                .fullName(personRequestDto.getFullName())
                .birthDate(DateConverter.converterStringToLocalDate(personRequestDto.getBirthDate()))
                .build();
    }

    public static List<PersonResponseDto> personsToPersonsResponseDto(List<Person> persons) {
        Function<Person, PersonResponseDto> converter =
                person -> PersonResponseDto.builder()
                        .birthDate(DateConverter.converterLocalDateToString(person.getBirthDate()))
                        .fullName(person.getFullName())
                    .build();
        return persons.stream().map(converter).collect(Collectors.toList());
    }

}
