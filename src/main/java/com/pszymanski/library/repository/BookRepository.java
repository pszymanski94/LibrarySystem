package com.pszymanski.library.repository;

import com.pszymanski.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByAuthor(String author);

    List<Book> findByTitle(String title);

    List<Book> findByYearOfPublication(int year);

    List<Book> findByCategory(String category);

    @Query("SELECT u FROM Book u where bookadd between ?1 AND ?2")
    List<Book> getNewBooks(LocalDate dateMinusSeven, LocalDate dateNow);

    @Modifying
    @Transactional
    @Query(value = "UPDATE books SET NUMER_BOOKS_AVAILABLE=?1 WHERE ID=?2", nativeQuery = true)
    void bookAdditionSubtraction(int number, int idBook);

    @Query(value = "SELECT SUM(number_books) FROM db_library.books WHERE date_adding_book BETWEEN CURDATE()-7 AND CURDATE()", nativeQuery = true)
    Integer bookAddingWeek();

    @Query(value = "SELECT SUM(number_books) FROM db_library.books WHERE date_adding_book BETWEEN CURDATE()-30 AND CURDATE()", nativeQuery = true)
    Integer bookAddingMonth();

}
