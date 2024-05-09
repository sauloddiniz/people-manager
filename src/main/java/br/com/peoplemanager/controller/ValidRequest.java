package br.com.peoplemanager.controller;

import br.com.peoplemanager.exception.PersonErrorException;
import org.springframework.validation.BindingResult;

import java.util.Objects;

public class ValidRequest {
    private ValidRequest(){}
    public static void valid(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            final String field = getFirstFieldContainsError(bindingResult);
            final String error = getFirstDefaultMessageError(bindingResult);
            throw new PersonErrorException(field.concat(": ").concat(Objects.requireNonNull(error)));
        }
    }

    private static String getFirstFieldContainsError(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().get(0).getField();
    }

    private static String getFirstDefaultMessageError(BindingResult bindingResult) {
        return bindingResult.getAllErrors().get(0).getDefaultMessage();
    }
}
