package com.pszymanski.library.dto;

import com.pszymanski.library.entity.BookCategory;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;

@lombok.Getter
@lombok.Setter
public class BookDTO extends BaseDTO {


    @NotNull
    @Size(max = 150)
    private String title;

    @NotNull
    @Size(max = 150)
    private String author;

    @NotNull
    @Size(max = 20)
    private String isbnCode;

    @NotNull
    @Digits(integer = 4, fraction = 0)
    private int yearOfPublication;

    @Enumerated(EnumType.STRING)
    private BookCategory category;

    @Size(max = 500)
    private String comments;

    @NotNull
    @Min(0)
    private int numberBooksAvailable;

    @NotNull
    @Min(0)
    private int numberBooks;

}
