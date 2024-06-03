package br.com.peoplemanager.domain.model;


import br.com.peoplemanager.domain.exception.PersonRequestBirthDateException;
import br.com.peoplemanager.domain.exception.PersonRequestFullNameException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

class PersonTest {
    @Test
    void shouldThrowErrorWhenNameNotContainsTwoWords() {
        assertThrows(PersonRequestFullNameException.class, () -> {
            new Person(1L, "Saulo", LocalDate.parse("1986-02-07"));
        });
    }

    @Test
    void shouldThrowErrorWhenNameIsEmpty() {
        assertThrows(PersonRequestFullNameException.class, () -> {
            new Person(1L, "", LocalDate.parse("1986-02-07"));
        });
    }

    @Test
    void shouldThrowErrorWhenNameIsNull() {
        assertThrows(PersonRequestFullNameException.class, () -> {
            new Person(1L, null, LocalDate.parse("1986-02-07"));
        });
    }

    @Test
    void shouldThrowErrorWhenBirthdayIsLessThen18() {
        assertThrows(PersonRequestBirthDateException.class, () -> {
            new Person(1L, "Saulo Dias", LocalDate.parse("2020-02-07"));
        });
    }

    @Test
    void shouldThrowErrorWhenBirthdayIsNull() {
        assertThrows(PersonRequestBirthDateException.class, () -> {
            new Person(1L, "Saulo Dias", null);
        });
    }
}