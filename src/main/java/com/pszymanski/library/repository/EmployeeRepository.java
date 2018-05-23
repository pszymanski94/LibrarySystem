package com.pszymanski.library.repository;

import com.pszymanski.library.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee findByUsername(String username);

    List<Employee> findByName(String name);
}
