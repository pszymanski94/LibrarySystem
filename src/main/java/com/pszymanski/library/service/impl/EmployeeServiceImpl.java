package com.pszymanski.library.service.impl;

import com.pszymanski.library.dto.EmployeeDTO;
import com.pszymanski.library.entity.Employee;
import com.pszymanski.library.repository.EmployeeRepository;
import com.pszymanski.library.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findOne(Integer id) {
        return employeeRepository.getOne(id);
    }

    @Override
    public List<Employee> search(String value, String option) {
        List<Employee> searchList = null;
        switch (option) {
            case "1":
                searchList = new ArrayList<>();
                searchList.add(employeeRepository.getOne(Integer.parseInt(value)));
                break;
            case "2":
                searchList = employeeRepository.findByName(value);
                break;
        }
        return searchList;
    }

    @Override
    public void dtoToEntity(EmployeeDTO object) {
        Employee employee = new Employee();
        if (object.getId() != null) employee.setId(object.getId());
        employee.setName(object.getName());
        employee.setDateOfBirth(object.getDateOfBirth());
        employee.setEmail(object.getEmail());
        employee.setPhone(object.getPhone());
        employee.setUsername(object.getUsername());
        employee.setPassword(passwordEncoder.encode(object.getPassword()));
        employee.setRole("ROLE_EMPLOYEE");
        save(employee);
    }

    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void delete(Integer id) {
        employeeRepository.deleteById(id);
    }
}
