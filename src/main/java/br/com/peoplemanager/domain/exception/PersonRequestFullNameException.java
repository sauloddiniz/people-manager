package br.com.peoplemanager.domain.exception;

public class PersonRequestFullNameException extends RuntimeException {
    public PersonRequestFullNameException(String fullName) {
        super(fullName);
    }
}
