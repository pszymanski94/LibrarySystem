package com.pszymanski.library.service.impl;

import com.pszymanski.library.dto.RentDTO;
import com.pszymanski.library.entity.Book;
import com.pszymanski.library.entity.Employee;
import com.pszymanski.library.entity.Rent;
import com.pszymanski.library.entity.User;
import com.pszymanski.library.repository.BookRepository;
import com.pszymanski.library.repository.EmployeeRepository;
import com.pszymanski.library.repository.RentRepository;
import com.pszymanski.library.repository.UserRepository;
import com.pszymanski.library.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
public class RentServiceImpl implements RentService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RentRepository rentRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    NotificationsServiceImpl notificationsService;

    @Override
    public List<Rent> findAll() {
        return rentRepository.findAll();
    }

    @Override
    public Rent findOne(Integer id) {
        return rentRepository.getOne(id);
    }

    @Override
    public List<Rent> search(String value, String option) {
        List<Rent> searchList = null;
        switch (option) {
            case "1":
                searchList = new ArrayList<>();
                searchList.add(rentRepository.getOne(Integer.parseInt(value)));
                break;
            case "2":
                User user = userRepository.getOne(Integer.parseInt(value));
                searchList = rentRepository.findByUser(user);
                break;
            case "3":
                Book book = bookRepository.getOne(Integer.parseInt(value));
                searchList = rentRepository.findByBook(book);
                break;
        }
        return searchList;
    }


    public void save(Rent rent, Integer plusMinus, int idBook) {
        rentRepository.save(rent);
        if (plusMinus != null)
            bookRepository.bookAdditionSubtraction(plusMinus, idBook);
    }

    public List<Rent> getRentsByUser(Integer id) {
        User user = userRepository.getOne(id);
        return rentRepository.findByUser(user);
    }

    public void addRent(RentDTO rentDTO, String userNameEmpl) {
        User user = userRepository.getOne(rentDTO.getUser());
        Book book = bookRepository.getOne(rentDTO.getBook());
        Employee employee = employeeRepository.findByUsername(userNameEmpl);
        Rent rent = new Rent();
        if (rentDTO.getId() != null) rent.setId(rentDTO.getId());
        rent.setUser(user);
        rent.setEmployee(employee);
        rent.setBook(book);
        if (rentDTO.getDateOfRental() == null) {
            rent.setDateOfRental(LocalDate.now());
        } else {
            rent.setDateOfRental(rentDTO.getDateOfRental());
        }
        if (rentDTO.getId() != null) rent.setDateOfFinalReturn(rentDTO.getDateOfFinalReturn());
        rent.setDateOfReturn(rentDTO.getDateOfReturn());
        Integer bookMinus;

        if (rentDTO.getId() == null)
            bookMinus = book.getNumberBooksAvailable() - 1;
        else {
            bookMinus = null;
        }

        save(rent, bookMinus, rentDTO.getBook());
    }

    public Double bookReturn(Integer idRent) {
        Rent rent = rentRepository.getOne(idRent);
        Book book = rent.getBook();
        Double penalty = calculatePenalty(rent.getDateOfReturn(), LocalDate.now());
        rent.setDateOfFinalReturn(LocalDate.now());
        rent.setPenalty(penalty);
        Integer bookPlus = book.getNumberBooksAvailable() + 1;
        save(rent, bookPlus, book.getId());

        notificationsService.sendInfoToUserReservatiom(book.getId());

        return penalty;
    }

    public Double calculatePenalty(LocalDate dateofReturn, LocalDate dateofFinal) {
        Double penalty;
        Period period = Period.between(dateofReturn, dateofFinal);
        Integer daysElapsed = period.getDays();

        if (daysElapsed < 4) {
            penalty = 0.0;
        } else if (daysElapsed > 4 && daysElapsed < 15) {
            penalty = 0.7 * daysElapsed;
        } else {
            penalty = 1.3 * daysElapsed;
        }

        BigDecimal bd = new BigDecimal(Double.toString(penalty));
        bd = bd.setScale(2, RoundingMode.HALF_UP);

        return bd.doubleValue();
    }

    @Override
    public void delete(Integer id) {
        rentRepository.deleteById(id);
    }

}
