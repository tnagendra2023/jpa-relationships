package com.java.jpa.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StudentDTOResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<CourseDTOResponse> courses;
}
