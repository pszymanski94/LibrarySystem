package com.pszymanski.library.repository;

import com.pszymanski.library.entity.Book;
import com.pszymanski.library.entity.Rent;
import com.pszymanski.library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface RentRepository extends JpaRepository<Rent,Integer> {

    List<Rent> findByUser (User user);

    List<Rent> findByBook(Book book);

    @Query(value = "Select * from rents where CURDATE() - date_of_return>3 AND date_of_final_return is null and send_email_user=0",nativeQuery = true)
    List<Rent> getOverdueRentForUser();

    @Modifying
    @Transactional
    @Query(value = "UPDATE rents SET send_email_user=1 WHERE id=?1",nativeQuery = true)
    void setEmailSentUser(Integer idRent);

    @Modifying
    @Transactional
    @Query(value = "UPDATE rents SET send_email_employee=1 WHERE id=?1",nativeQuery = true)
    void setEmailSentEmployee(Integer idRent);

    @Query(value = "SELECT COUNT(*) FROM db_library.rents WHERE date_of_rental BETWEEN CURDATE()-7 AND CURDATE()", nativeQuery = true)
    Integer rentsAddingWeek();

    @Query(value = "SELECT COUNT(*) FROM db_library.rents WHERE date_of_rental BETWEEN CURDATE()-30 AND CURDATE()", nativeQuery = true)
    Integer rentsAddingMonth();



}
