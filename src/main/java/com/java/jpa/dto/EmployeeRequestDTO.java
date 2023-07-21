package com.java.jpa.dto;

import lombok.Data;

@Data
public class EmployeeRequestDTO {
    private String name;
    private int age;
    private SalaryDetailsRequestDTO salaryDetails;
}
