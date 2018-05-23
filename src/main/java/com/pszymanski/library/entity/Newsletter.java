package com.pszymanski.library.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "newsletters")
@lombok.Getter
@lombok.Setter
public class Newsletter extends BaseEntity {

    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    @Column(name = "EMAIL", nullable = false, length = 50, unique=true)
    private String email;
}
