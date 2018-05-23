package com.pszymanski.library.service;

import com.pszymanski.library.entity.Book;
import com.pszymanski.library.entity.Reservation;

import java.util.List;

public interface ReservationService {

    void save(Reservation reservation);

    void reservationByUser(String name, Integer id);

    void delete(Integer id);

    List<Reservation> findAll();

    Reservation findOne(Integer id);

    List<Reservation> search(String value, String option);



    }
