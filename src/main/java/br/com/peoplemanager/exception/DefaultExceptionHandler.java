package br.com.peoplemanager.exception;
import br.com.peoplemanager.dto.error.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PersonErrorException.class)
    public ResponseEntity<ErrorResponseDto> clientApiFeingException(PersonErrorException exception, HttpServletRequest request){
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
    public ResponseEntity<ErrorResponseDto> zeroResultsAddressException(PersonNotFoundException exception, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponseDto
                        .builder()
                        .message(exception.getMessage())
                        .status(HttpStatus.NOT_FOUND.value())
                        .path(request.getServletPath())
                        .method(request.getMethod())
                        .build());
    }
//
//    @ExceptionHandler(AddressBlankException.class)
//    public ResponseEntity<ErrorResponseDto> addressBlankException(AddressBlankException exception, HttpServletRequest request){
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .body(ErrorResponseDto
//                        .builder()
//                        .message(exception.getMessage())
//                        .status(HttpStatus.BAD_REQUEST.value())
//                        .path(request.getServletPath())
//                        .method(request.getMethod())
//                        .build());
//    }
//    @ExceptionHandler(GenericAddressException.class)
//    public ResponseEntity<ErrorResponseDto> genericAddressException(GenericAddressException exception, HttpServletRequest request){
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .body(ErrorResponseDto
//                        .builder()
//                        .message(exception.getMessage())
//                        .status(HttpStatus.BAD_REQUEST.value())
//                        .path(request.getServletPath())
//                        .method(request.getMethod())
//                        .build());
//    }
//    @ExceptionHandler(InsufficientAddressException.class)
//    public ResponseEntity<ErrorResponseDto> insufficientAddressException(InsufficientAddressException exception, HttpServletRequest request){
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .body(ErrorResponseDto
//                        .builder()
//                        .message(exception.getMessage())
//                        .status(HttpStatus.BAD_REQUEST.value())
//                        .path(request.getServletPath())
//                        .method(request.getMethod())
//                        .build());
//    }
//
//    @ExceptionHandler(FormattedAddressNullException.class)
//    public ResponseEntity<ErrorResponseDto> formattedAddressNullException(FormattedAddressNullException exception, HttpServletRequest request){
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(ErrorResponseDto
//                        .builder()
//                        .message(exception.getMessage())
//                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
//                        .path(request.getServletPath())
//                        .method(request.getMethod())
//                        .build());
//    }
//    @ExceptionHandler(AddressGeometryNullException.class)
//    public ResponseEntity<ErrorResponseDto> addressGeometryNullException(AddressGeometryNullException exception, HttpServletRequest request){
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(ErrorResponseDto
//                        .builder()
//                        .message(exception.getMessage())
//                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
//                        .path(request.getServletPath())
//                        .method(request.getMethod())
//                        .build());
//    }
//    @ExceptionHandler(AddressGeolocationNullException.class)
//    public ResponseEntity<ErrorResponseDto> addressGeolocationNullException(AddressGeolocationNullException exception, HttpServletRequest request){
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(ErrorResponseDto
//                        .builder()
//                        .message(exception.getMessage())
//                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
//                        .path(request.getServletPath())
//                        .method(request.getMethod())
//                        .build());
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid
//            (MethodArgumentNotValidException ex, HttpHeaders headers,
//             HttpStatusCode status, WebRequest request) {
//
//        List<ErrorFieldDto> listError = ex.getFieldErrors()
//                .stream().map(ErrorFieldDto::converter)
//                .toList();
//
//        String path = getPath((ServletWebRequest) request);
//
//        return ResponseEntity.badRequest().body(
//                ErrorResponseDto
//                        .builder()
//                        .errors(listError)
//                        .status(status.value())
//                        .path(path)
//                        .build());
//    }
//
//    private static String getPath(ServletWebRequest request) {
//        return request.getRequest().getRequestURI();
//    }
}
