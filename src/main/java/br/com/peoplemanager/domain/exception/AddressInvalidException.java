package br.com.peoplemanager.domain.exception;

public class AddressInvalidException extends RuntimeException {
    public AddressInvalidException(String message) {
        super(message);
    }
}
