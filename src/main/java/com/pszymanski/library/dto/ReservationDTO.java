package com.pszymanski.library.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@lombok.Getter
@lombok.Setter
public class ReservationDTO extends BaseDTO {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfReservation;
    @NotNull
    @Min(1)
    private Integer user;

    private Integer employee;

    @NotNull
    @Min(1)
    private Integer book;
}
