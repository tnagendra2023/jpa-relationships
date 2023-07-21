package com.java.jpa.service;

import com.java.jpa.dto.CourseDTORequest;
import com.java.jpa.dto.CourseDTOResponse;
import com.java.jpa.dto.StudentDTORequest;
import com.java.jpa.dto.StudentDTOResponse;
import com.java.jpa.entity.Course;
import com.java.jpa.entity.Student;
import com.java.jpa.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentDTOResponse addStudent(StudentDTORequest studentDTORequest){
        Student student = prepareStudentRequestInfo(studentDTORequest);
        studentRepository.save(student);
        return prepareStudentResponseInfo(student);
    }

    private static Student prepareStudentRequestInfo(StudentDTORequest studentDTORequest) {
        Student student=new Student();
        student.setFirstName(studentDTORequest.getFirstName());
        student.setLastName(studentDTORequest.getLastName());
        student.setEmail(studentDTORequest.getEmail());

        List<CourseDTORequest> courses = studentDTORequest.getCourses();
        List<Course> courseList=new ArrayList<>();
        courses.forEach(courseDTORequest -> {
            Course course=new Course();
            course.setName(courseDTORequest.getName());
            courseList.add(course);
        });
        student.setCourses(courseList);
        return student;
    }

    private static StudentDTOResponse prepareStudentResponseInfo(Student student) {
       List<CourseDTOResponse> courseDTOResponseList=new ArrayList<>();
        List<Course> courses = student.getCourses();
        courses.forEach(course -> courseDTOResponseList.add(CourseDTOResponse.builder()
                .id(course.getId())
                .name(course.getName())
                .build()));

        return StudentDTOResponse.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .courses(courseDTOResponseList)
                .build();
    }
}
