package com.employemanagement.project.repository;

import com.employemanagement.project.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

//Methods to be performed for CRUD operations
public interface EmployeeRepository extends JpaRepository<Employee,Long> {



}
