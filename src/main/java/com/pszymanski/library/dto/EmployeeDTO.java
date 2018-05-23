package com.pszymanski.library.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

@lombok.Getter
@lombok.Setter
public class EmployeeDTO extends BaseDTO {

    @NotEmpty
    @Size(max = 150)
    @Pattern(regexp = "^[\\p{IsAlphabetic}\\-\\s]*$")
    private String name;

    @NotNull
    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @NotEmpty
    @Pattern(regexp = "^[0-9\\-\\+\\(\\)]*$")
    @Size(max = 50)
    private String phone;

    @NotEmpty
    @Email
    @Size(max = 50)
    private String email;

    @NotEmpty
    @Size(max = 50)
    private String username;

    @NotEmpty
    @Size(max = 50)
    private String password;

}
