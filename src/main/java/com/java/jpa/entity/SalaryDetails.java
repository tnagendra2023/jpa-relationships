package com.java.jpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "salary")
public class SalaryDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double salary;
    private String taxCategory;
    @OneToOne
    @JoinColumn(name = "employee_id",referencedColumnName = "id")
    private Employee employee;

    public SalaryDetails(double salary, String taxCategory, Employee employee) {
        this.salary = salary;
        this.taxCategory = taxCategory;
        this.employee = employee;
    }

    public double getSalary() {
        return salary;
    }

    public String getTaxCategory() {
        return taxCategory;
    }

    public Employee getEmployee() {
        return employee;
    }
}
