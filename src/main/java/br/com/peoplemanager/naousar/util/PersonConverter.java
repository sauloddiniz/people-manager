//package br.com.peoplemanager.naousar.util;
//
//import br.com.peoplemanager.infrastructure.controller.dto.PersonDto;
//import br.com.peoplemanager.infrastructure.persistence.model.PersonEntity;
//
//import java.util.List;
//import java.util.function.Function;
//
//public class PersonConverter {
//
//    private PersonConverter(){}
//    public static PersonEntity personDtoToPerson(PersonDto personRequestDto) {
//        return PersonEntity.builder()
//                .fullName(personRequestDto.getFullName())
//                .birthDate(DateConverter.converterStringToLocalDate(personRequestDto.getBirthDate()))
//                .build();
//    }
//
//    public static List<PersonDto> personsToPersonsDto(List<PersonEntity> persons) {
//        Function<PersonEntity, PersonDto> converter =
//                person -> PersonDto.builder()
//                        .personId(String.valueOf(person.getPersonId()))
//                        .birthDate(DateConverter.converterLocalDateToString(person.getBirthDate()))
//                        .fullName(person.getFullName())
//                    .build();
//        return persons.stream().map(converter).toList();
//    }
//
//}
