package com.java.jpa.dto;

import lombok.Data;

import java.util.List;

@Data
public class StudentRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private List<CourseRequestDTO> courses;
}
