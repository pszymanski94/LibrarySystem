package com.pszymanski.library.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "rents")
@lombok.Getter
@lombok.Setter
public class Rent extends BaseEntity {

    @Column(name = "DATE_OF_RENTAL", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfRental;

    @Column(name = "DATE_OF_RETURN", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfReturn;

    @Column(name = "DATE_OF_FINAL_RETURN", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfFinalReturn;

    @Column(name = "PENALTY")
    private double penalty;

    @Column(name = "SEND_EMAIL_USER")
    private boolean sendEmailUser;

    @Column(name = "SEND_EMAIL_EMPLOYEE")
    private double sendEmailEmployee;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID", nullable = true)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "BOOK_ID", nullable = false)
    private Book book;


}
