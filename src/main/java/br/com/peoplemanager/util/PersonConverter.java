package br.com.peoplemanager.util;

import br.com.peoplemanager.dto.person.PersonDto;
import br.com.peoplemanager.entity.Person;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PersonConverter {

    private PersonConverter(){};
    public static Person personDtoToPerson(PersonDto personRequestDto) {
        return Person.builder()
                .fullName(personRequestDto.getFullName())
                .birthDate(DateConverter.converterStringToLocalDate(personRequestDto.getBirthDate()))
                .build();
    }

    public static List<PersonDto> personsToPersonsDto(List<Person> persons) {
        Function<Person, PersonDto> converter =
                person -> PersonDto.builder()
                        .birthDate(DateConverter.converterLocalDateToString(person.getBirthDate()))
                        .fullName(person.getFullName())
                    .build();
        return persons.stream().map(converter).collect(Collectors.toList());
    }

}
