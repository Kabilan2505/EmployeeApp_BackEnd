package com.msp.Employee.Application.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.msp.Employee.Application.Entity.Employees;

@Repository
public interface EmployeeRepository extends JpaRepository<Employees, Long> {

}
