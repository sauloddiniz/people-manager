package br.com.peoplemanager.dto.person;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PersonDto {
    private String personId;
    @NotEmpty
    @Pattern(regexp="^\\w+\\s+\\w+.*$", message="Full name must contain at least two words.")
    private String fullName;
    @NotEmpty
    private String birthDate;
}
