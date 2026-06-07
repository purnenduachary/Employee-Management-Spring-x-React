package com.employemanagement.project.controller;

import com.employemanagement.project.dto.EmployeeDto;
import com.employemanagement.project.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {

    //Interface object
    private EmployeeService employeeService;

    //Build Add Employee REST API
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto)
    //RequestBody = Extract the JSON and convert them into Java Objects
    {
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    //Build Get employee REST API
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable("id") Long employeeId) {
        EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
//       return new ResponseEntity<>(employeeDto, HttpStatus.OK);
        return ResponseEntity.ok(employeeDto);
    }

    //Build get all employees REST API
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> employeeDtos = employeeService.getAllEmployees();
        return new ResponseEntity<>(employeeDtos, HttpStatus.OK);
    }

    //Build update employees REST API
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId, @RequestBody EmployeeDto updateEmployeeDto){
        EmployeeDto updatedEmployeeDto =  employeeService.updateEmployee(employeeId, updateEmployeeDto);
        return new ResponseEntity<>(updatedEmployeeDto, HttpStatus.ACCEPTED);
    }

    //Build delete employee REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long employeeId){
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>("Employee Deleted Successfully!", HttpStatus.ACCEPTED);
    }

}
