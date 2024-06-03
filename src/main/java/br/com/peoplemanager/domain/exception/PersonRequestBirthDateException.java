package br.com.peoplemanager.domain.exception;

public class PersonRequestBirthDateException extends RuntimeException {
    public PersonRequestBirthDateException(String birthDate) {
        super(birthDate);
    }
}
