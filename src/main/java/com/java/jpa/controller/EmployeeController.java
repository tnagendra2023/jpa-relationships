package com.java.jpa.controller;

import com.java.jpa.dto.EmployeeRequestDTO;
import com.java.jpa.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller performs @OneToOne Bidirectional JPA mapping
 */
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeRequestDTO> addEmployee(@RequestBody EmployeeRequestDTO employeeRequestDTO) {
        return new ResponseEntity<>(employeeService.addEmployee(employeeRequestDTO), HttpStatus.CREATED);
    }
}
