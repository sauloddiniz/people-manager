package br.com.peoplemanager.naousar.dto.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ErrorResponseDto {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    @Builder.Default()
    private String date = String.valueOf(Date.from(Instant.now()));
    private int status;
    private String path;
    private String message;
    private String method;
}
