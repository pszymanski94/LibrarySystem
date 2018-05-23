package com.pszymanski.library.service;

import com.pszymanski.library.dto.BookDTO;
import com.pszymanski.library.entity.Book;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.List;

public interface BookService {


    List<Book> findAll();

    Book findOne(Integer id);

    List<Book> search(String value, String option);

    Book dtoToEntity(BookDTO object);

    void save(BookDTO book);

    void delete(Integer id);

}

