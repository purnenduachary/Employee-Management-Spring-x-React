package com.employemanagement.project.mapper;

import com.employemanagement.project.dto.EmployeeDto;
import com.employemanagement.project.entity.Employee;

public class EmployeeMapper {

//    JSON Request
//         ▼
//    EmployeeDto
//         ▼
//    Controller
//         ▼
//      Service
//         ▼
//    mapToEmployee()
//         ▼
//    Employee Entity
//         ▼
//    Repository.save()
//         ▼
//      Hibernate
//         ▼
//       MySQL
//         ▼
//    Employee Entity (id generated)
//         ▼
//    mapToEmployeeDto()
//         ▼
//    EmployeeDto
//         ▼
//    Controller
//         ▼
//    JSON Response


    //Static method to convert EmployeeDto to Employee Entity
    //Used first when data is coming from the client and we want to save it in the database, we need to convert the DTO to Entity
    public static Employee mapToEmployee(EmployeeDto employeeDto) {
        return new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail()
        );
    }

    // Static method to convert Employee entity to EmployeeDto
    //User second when data is coming from the database and we want to send it to the client, we need to convert the Entity to DTO
    public static EmployeeDto mapToEmployeeDto(Employee employee) {
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        );
    }


}
