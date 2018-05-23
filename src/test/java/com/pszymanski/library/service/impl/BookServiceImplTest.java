package com.pszymanski.library.service.impl;

import com.pszymanski.library.dto.BookDTO;
import com.pszymanski.library.entity.Book;
import com.pszymanski.library.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class BookServiceImplTest {

    private BookServiceImpl bookServiceImplUnderTest;

    @BeforeEach
    public void setUp() {
        bookServiceImplUnderTest = new BookServiceImpl();
        bookServiceImplUnderTest.bookRepository = mock(BookRepository.class);
    }

    @Test
    public void testFindAll() {

        final List<Book> expectedResult = Arrays.asList();
        final List<Book> result = bookServiceImplUnderTest.findAll();

        assertEquals(expectedResult, result);
    }

    @Test
    public void testFindOne() {
        final Integer id = 3;
        final Book expectedResult = bookServiceImplUnderTest.bookRepository.getOne(id);
        final Book result = bookServiceImplUnderTest.findOne(id);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testSearch() {
        final String value = "4";
        final String option = "1";
        final List<Book> expectedResult = Arrays.asList();
        final List<Book> result = bookServiceImplUnderTest.search(value, option);

        assertEquals(expectedResult, result);
    }



    @Test
    public void testSave() {
        final BookDTO book = new BookDTO();

        bookServiceImplUnderTest.save(book);

    }

    @Test
    public void testDelete() {
        final Integer id = 3;

        bookServiceImplUnderTest.delete(id);

    }
}
