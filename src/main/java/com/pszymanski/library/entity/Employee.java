package com.pszymanski.library.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "employees")
@lombok.Getter
@lombok.Setter
public class Employee extends BaseEntity {

    @Column(name = "NAME", nullable = false, length = 150)
    private String name;

    @Column(name = "DATE_OF_BIRTH")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @Column(name = "PHONE", nullable = false, length = 50)
    private String phone;

    @Column(name = "EMAIL", nullable = true, length = 100, unique=true)
    private String email;

    @Column(name = "USERNAME", nullable = false, length = 50, unique=true)
    private String username;

    @Column(name = "PASSWORD", nullable = false, length = 60)
    private String password;

    @Column(name = "ROLE", nullable = false, length = 50)
    private String role;

    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "user")
    private List<Rent> rents;


}
