package br.com.peoplemanager.dto.person;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PersonDto {
    @NotEmpty
    private String fullName;
    @NotEmpty
    private String birthDate;
}
