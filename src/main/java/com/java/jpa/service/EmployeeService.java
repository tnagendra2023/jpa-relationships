package com.java.jpa.service;

import com.java.jpa.dto.EmployeeRequestDTO;
import com.java.jpa.dto.SalaryDetailsRequestDTO;
import com.java.jpa.entity.Employee;
import com.java.jpa.entity.SalaryDetails;
import com.java.jpa.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeRequestDTO addEmployee(EmployeeRequestDTO employeeRequestDTO){
        Employee employee=new Employee();
        employee.setAge(employeeRequestDTO.getAge());
        employee.setName(employeeRequestDTO.getName());
        SalaryDetailsRequestDTO salaryDetailsDTO = employeeRequestDTO.getSalaryDetails();
        SalaryDetails salaryDetails=new SalaryDetails(salaryDetailsDTO.getSalary(),salaryDetailsDTO.getTaxCategory(),employee);
        employee.setSalaryDetails(salaryDetails);
        employeeRepository.save(employee);
        return employeeRequestDTO;
    }
}
