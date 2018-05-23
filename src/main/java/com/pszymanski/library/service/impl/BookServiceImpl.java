package com.pszymanski.library.service.impl;

import com.pszymanski.library.dto.BookDTO;
import com.pszymanski.library.entity.Book;
import com.pszymanski.library.entity.Newsletter;
import com.pszymanski.library.repository.BookRepository;
import com.pszymanski.library.repository.NewsletterRepository;
import com.pszymanski.library.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findOne(Integer id) {
        return bookRepository.getOne(id);
    }

    @Override
    public List<Book> search(String value, String option) {
        List<Book> searchList = null;
        switch (option) {
            case "1":
                searchList = bookRepository.findByTitle(value);
                break;
            case "2":
                searchList = bookRepository.findByAuthor(value);
                break;
            case "3":
                searchList = bookRepository.findByYearOfPublication(Integer.parseInt(value));
                break;
            case "4":
                searchList = bookRepository.findByCategory(value);
                break;
        }
        return searchList;
    }

    @Override
    public Book dtoToEntity(BookDTO object) {

        Book book = new Book();
        if (object.getId() != null) book.setId(object.getId());
        book.setAuthor(object.getAuthor());
        book.setCategory(object.getCategory());
        book.setTitle(object.getTitle());
        book.setIsbnCode(object.getIsbnCode());
        book.setYearOfPublication(object.getYearOfPublication());
        book.setComments(object.getComments());
        book.setDateAddingBook(LocalDate.now());
        book.setNumberBooks(object.getNumberBooks());
        book.setNumberBooksAvailable(object.getNumberBooksAvailable());
        return book;
    }

    @Override
    public void save(BookDTO book) {
        bookRepository.save(dtoToEntity(book));
    }

    @Override
    public void delete(Integer id) {
        bookRepository.deleteById(id);
    }

}
