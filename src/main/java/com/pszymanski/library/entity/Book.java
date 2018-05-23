package com.pszymanski.library.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "books")
@lombok.Getter
@lombok.Setter
public class Book extends BaseEntity {

    @Column(name = "TITLE", nullable = false, length = 150)
    private String title;

    @Column(name = "AUTHOR", nullable = false, length = 150)
    private String author;

    @Column(name = "ISBN", nullable = false, length = 20)
    private String isbnCode;

    @Column(name = "YEAR_OF_PUBLICATION", nullable = false)
    private int yearOfPublication;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "CATEGORY")
    private BookCategory category;

    @Column(name = "COMMENTS", nullable = true, length = 500)
    private String comments;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "DATE_ADDING_BOOK", nullable = false)
    private LocalDate dateAddingBook;

    @Column(name = "NUMER_BOOKS_AVAILABLE")
    private int numberBooksAvailable;

    @Column(name = "NUMBER_BOOKS")
    private int numberBooks;

}
