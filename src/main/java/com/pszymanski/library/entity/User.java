package com.pszymanski.library.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
@lombok.Getter
@lombok.Setter
public class User extends BaseEntity {

    @Column(name = "NAME", nullable = false, length = 150)
    private String name;

    @Column(name = "DATE_OF_BIRTH", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @Column(name = "STREET", nullable = false, length = 100)
    private String street;

    @Column(name = "CITY", nullable = false, length = 100)
    private String city;

    @Column(name = "PHONE", nullable = false, length = 50)
    private String phone;

    @Column(name = "EMAIL", nullable = true, length = 50, unique=true)
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
