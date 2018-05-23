package com.pszymanski.library.service.impl;

import com.pszymanski.library.repository.BookRepository;
import com.pszymanski.library.repository.RentRepository;
import com.pszymanski.library.repository.ReservationRepository;
import com.pszymanski.library.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    RentRepository rentRepository;
    @Autowired
    ReservationRepository reservationRepository;

    public List<Integer> getStatistic() {
        List<Integer> staticList = new ArrayList<>();

        staticList.add(bookRepository.bookAddingWeek());
        staticList.add(bookRepository.bookAddingMonth());
        staticList.add(rentRepository.rentsAddingWeek());
        staticList.add(rentRepository.rentsAddingMonth());
        staticList.add(reservationRepository.reservationAddingWeek());
        staticList.add(reservationRepository.reservationAddingMonth());

        return staticList;

    }

}
