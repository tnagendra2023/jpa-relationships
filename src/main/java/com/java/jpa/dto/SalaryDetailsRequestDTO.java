package com.java.jpa.dto;

import lombok.Data;

@Data
public class SalaryDetailsRequestDTO {
    private double salary;
    private String taxCategory;
}
