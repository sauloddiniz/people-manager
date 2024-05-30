package br.com.peoplemanager.domain.exception;

public class PersonErrorException extends RuntimeException {
    public PersonErrorException(String message) {
        super(message);
    }
}
