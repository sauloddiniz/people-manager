package br.com.peoplemanager.exception;

public class PersonErrorException extends RuntimeException {
    public PersonErrorException(String message) {
        super(message);
    }
}
