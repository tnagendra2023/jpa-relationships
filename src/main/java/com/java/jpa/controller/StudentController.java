package com.java.jpa.controller;

import com.java.jpa.dto.StudentDTORequest;
import com.java.jpa.dto.StudentDTOResponse;
import com.java.jpa.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller performs @ManyToMany Bidirectional JPA mapping
 */
@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentDTOResponse> addStudent(@RequestBody StudentDTORequest studentDTORequest) {
        return new ResponseEntity<>(studentService.addStudent(studentDTORequest), HttpStatus.CREATED);
    }
}
