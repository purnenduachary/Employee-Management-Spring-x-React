package com.employemanagement.project.service.impl;

import com.employemanagement.project.dto.EmployeeDto;
import com.employemanagement.project.entity.Employee;
import com.employemanagement.project.exceptions.ResourceNotFoundException;
import com.employemanagement.project.mapper.EmployeeMapper;
import com.employemanagement.project.repository.EmployeeRepository;
import com.employemanagement.project.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    //Repository Interface Object to obtain all CRUD methods
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        // Implementation for creating an employee
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with Id: " +  employeeId));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {

//        employeeRepository.findAll().stream().map(EmployeeMapper::mapToEmployeeDto).toList();
       List<Employee> employees =  employeeRepository.findAll();
        return employees.stream().map(EmployeeMapper::mapToEmployeeDto).toList();
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updateEmployeeDto) {

        employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with Id: " +  employeeId));
        Employee employee = EmployeeMapper.mapToEmployee(updateEmployeeDto);
        employee.setId(employeeId);
        employee.setFirstName(updateEmployeeDto.getFirstName());
        employee.setLastName(updateEmployeeDto.getLastName());
        employee.setEmail(updateEmployeeDto.getEmail());
        Employee updatedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with Id: " +  employeeId));
        employeeRepository.deleteById(employeeId);
    }

}
