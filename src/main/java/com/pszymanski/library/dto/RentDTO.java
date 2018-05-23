package com.pszymanski.library.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@lombok.Getter
@lombok.Setter
public class RentDTO extends BaseDTO {

    @NotNull
    @Min(1)
    private Integer user;
    @NotNull
    @Min(1)
    private Integer book;

    private Integer employee = null;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfRental = null;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfReturn;

    private Double penalty;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfFinalReturn;

}
