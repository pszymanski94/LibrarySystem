package com.pszymanski.library.service;

import com.pszymanski.library.dto.RentDTO;
import com.pszymanski.library.entity.Rent;

import java.time.LocalDate;
import java.util.List;

public interface RentService {

    void save(Rent rent, Integer plusMinus, int idBook);

    List<Rent> getRentsByUser(Integer id);

    void addRent(RentDTO rentDTO, String userNameEmpl);

    Double bookReturn(Integer idRent);

    Double calculatePenalty(LocalDate dateofReturn, LocalDate dateofFinal);

    List<Rent> findAll();

    Rent findOne(Integer id);

    List<Rent> search(String value, String option);

    void delete(Integer id);


}
