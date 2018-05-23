package com.pszymanski.library.repository;

import com.pszymanski.library.entity.Book;
import com.pszymanski.library.entity.Reservation;
import com.pszymanski.library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findByUser(User user);

    List<Reservation> findByBook(Book book);

    @Query(value = "SELECT * FROM reservations WHERE book_id=?1 ORDER BY ID LIMIT 1", nativeQuery = true)
    Reservation findReservationForUser(Integer idBook);

    @Query(value = "SELECT COUNT(*) FROM reservations WHERE date_of_reservation BETWEEN CURDATE()-7 AND CURDATE()", nativeQuery = true)
    Integer reservationAddingWeek();

    @Query(value = "SELECT COUNT(*) FROM reservations WHERE date_of_reservation BETWEEN CURDATE()-30 AND CURDATE()", nativeQuery = true)
    Integer reservationAddingMonth();

    @Modifying
    @Transactional
    @Query(value = "UPDATE reservations SET send_email=1 WHERE id=?1", nativeQuery = true)
    void sendEmail(Integer idReservation);


}
