package br.com.peoplemanager.infrastructure.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private String path;
    private int httpStatus;
    private String message;
    private String method;
}
