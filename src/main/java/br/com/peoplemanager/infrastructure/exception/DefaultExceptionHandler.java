package br.com.peoplemanager.infrastructure.exception;

import br.com.peoplemanager.domain.exception.AddressNotFoundException;
import br.com.peoplemanager.domain.exception.PersonErrorException;
import br.com.peoplemanager.domain.exception.PersonNotFoundException;
import br.com.peoplemanager.domain.exception.StateEnumConverterException;
import br.com.peoplemanager.naousar.dto.error.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PersonErrorException.class)
    public ResponseEntity<ErrorResponseDto> personError(PersonErrorException exception, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponseDto
                        .builder()
                        .message(exception.getMessage())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .path(request.getServletPath())
                        .method(request.getMethod())
                        .build());
    }

    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> personNotFoundException(PersonNotFoundException exception, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponseDto
                        .builder()
                        .message(exception.getMessage())
                        .status(HttpStatus.NOT_FOUND.value())
                        .path(request.getServletPath())
                        .method(request.getMethod())
                        .build());
    }

    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> addressNotFoundException(AddressNotFoundException exception, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponseDto
                        .builder()
                        .message(exception.getMessage())
                        .status(HttpStatus.NOT_FOUND.value())
                        .path(request.getServletPath())
                        .method(request.getMethod())
                        .build());
    }

    @ExceptionHandler(StateEnumConverterException.class)
    public ResponseEntity<ErrorResponseDto> stateEnumConverterException(StateEnumConverterException exception, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponseDto
                        .builder()
                        .message(exception.getMessage())
                        .status(HttpStatus.NOT_FOUND.value())
                        .path(request.getServletPath())
                        .method(request.getMethod())
                        .build());
    }
}
