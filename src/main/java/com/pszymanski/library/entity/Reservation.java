package com.pszymanski.library.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "reservations")
@lombok.Getter
@lombok.Setter
public class Reservation extends BaseEntity {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "DATE_OF_RESERVATION", nullable = false)
    private LocalDate dateOfReservation;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID", nullable = true)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "BOOK_ID", nullable = false)
    private Book book;

    @Column(name = "SEND_EMAIL")
    private boolean sendEmail;

}
