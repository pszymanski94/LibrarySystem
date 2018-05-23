package com.pszymanski.library.service;

import com.pszymanski.library.dto.EmployeeDTO;
import com.pszymanski.library.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findOne(Integer id);

    List<Employee> search(String value, String options);

    void dtoToEntity(EmployeeDTO user);

    void save(Employee employee);

    void delete(Integer id);
}
