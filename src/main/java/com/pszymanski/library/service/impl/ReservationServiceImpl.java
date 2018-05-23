package com.pszymanski.library.service.impl;

import com.pszymanski.library.dto.ReservationDTO;
import com.pszymanski.library.entity.*;
import com.pszymanski.library.repository.BookRepository;
import com.pszymanski.library.repository.EmployeeRepository;
import com.pszymanski.library.repository.ReservationRepository;
import com.pszymanski.library.repository.UserRepository;
import com.pszymanski.library.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation findOne(Integer id) {
        return reservationRepository.getOne(id);
    }

    @Override
    public List<Reservation> search(String value, String option) {
        List<Reservation> searchList = null;
        switch (option) {
            case "1":
                searchList = new ArrayList<>();
                searchList.add(reservationRepository.getOne(Integer.parseInt(value)));
                break;
            case "2":
                searchList = reservationRepository.findByUser(userRepository.getOne(Integer.parseInt(value)));
                break;
            case "3":
                searchList = reservationRepository.findByBook(bookRepository.getOne(Integer.parseInt(value)));
                break;
        }
        return searchList;
    }

    public void save(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    public void reservationByUser(String name, Integer id) {
        Reservation reservation = new Reservation();
        User user = userRepository.findByUsername(name);
        Book book = bookRepository.getOne(id);
        reservation.setDateOfReservation(LocalDate.now());
        reservation.setUser(user);
        reservation.setBook(book);
        save(reservation);
    }

    public void reservByEmployee(ReservationDTO reservationDTO, String name) {
        Reservation reservation = new Reservation();
        User user = userRepository.getOne(reservationDTO.getUser());
        Employee employee = employeeRepository.findByUsername(name);
        Book book = bookRepository.getOne(reservationDTO.getBook());
        if (reservationDTO.getId() != null) reservation.setId(reservationDTO.getId());
        if (reservationDTO.getId() != null) reservation.setDateOfReservation(reservationDTO.getDateOfReservation());
        else reservation.setDateOfReservation(LocalDate.now());
        reservation.setUser(user);
        reservation.setBook(book);
        reservation.setEmployee(employee);
        save(reservation);
    }

    public List<Reservation> getReservationByUser(Integer id) {
        User user = userRepository.getOne(id);
        return reservationRepository.findByUser(user);
    }

    @Override
    public void delete(Integer id) {
        reservationRepository.deleteById(id);
    }

}
