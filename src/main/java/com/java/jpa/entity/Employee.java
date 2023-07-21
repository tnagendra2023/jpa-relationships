package com.java.jpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;
    @OneToOne(mappedBy = "employee",cascade = CascadeType.ALL,orphanRemoval = true)
    private SalaryDetails salaryDetails;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSalaryDetails(SalaryDetails salaryDetails) {
        this.salaryDetails = salaryDetails;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public SalaryDetails getSalaryDetails() {
        return salaryDetails;
    }
}
