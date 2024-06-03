package br.com.peoplemanager.infrastructure.exception;

import br.com.peoplemanager.domain.exception.AddressNotFoundException;
import br.com.peoplemanager.domain.exception.PersonErrorException;
import br.com.peoplemanager.domain.exception.PersonNotFoundException;
import br.com.peoplemanager.domain.exception.StateEnumConverterException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PersonErrorException.class)
    public ResponseEntity<ErrorResponse> personError(PersonErrorException exception, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse
                        .builder()
                        .message(exception.getMessage())
                        .httpStatus(HttpStatus.BAD_REQUEST.value())
                        .path(request.getServletPath())
                        .method(request.getMethod())
                        .build());
    }

    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<ErrorResponse> personNotFoundException(PersonNotFoundException exception, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse
                        .builder()
                        .message(exception.getMessage())
                        .httpStatus(HttpStatus.NOT_FOUND.value())
                        .path(request.getServletPath())
                        .method(request.getMethod())
                        .build());
    }

    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<ErrorResponse> addressNotFoundException(AddressNotFoundException exception, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse
                        .builder()
                        .message(exception.getMessage())
                        .httpStatus(HttpStatus.NOT_FOUND.value())
                        .path(request.getServletPath())
                        .method(request.getMethod())
                        .build());
    }

    @ExceptionHandler(StateEnumConverterException.class)
    public ResponseEntity<ErrorResponse> stateEnumConverterException(StateEnumConverterException exception, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse
                        .builder()
                        .message(exception.getMessage())
                        .httpStatus(HttpStatus.NOT_FOUND.value())
                        .path(request.getServletPath())
                        .method(request.getMethod())
                        .build());
    }
}
