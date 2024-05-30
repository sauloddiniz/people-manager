package br.com.peoplemanager.domain.exception;

import java.time.LocalDate;

public class PersonRequestBirthDateException extends RuntimeException {
    public PersonRequestBirthDateException(String birthDate) {
        super(birthDate);
    }
}
