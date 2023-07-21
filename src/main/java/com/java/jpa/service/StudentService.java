package com.java.jpa.service;

import com.java.jpa.dto.CourseRequestDTO;
import com.java.jpa.dto.CourseResponseDTO;
import com.java.jpa.dto.StudentRequestDTO;
import com.java.jpa.dto.StudentResponseDTO;
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

    public StudentResponseDTO addStudent(StudentRequestDTO studentRequestDTO){
        Student student = prepareStudentRequestInfo(studentRequestDTO);
        studentRepository.save(student);
        return prepareStudentResponseInfo(student);
    }

    private static Student prepareStudentRequestInfo(StudentRequestDTO studentRequestDTO) {
        Student student=new Student();
        student.setFirstName(studentRequestDTO.getFirstName());
        student.setLastName(studentRequestDTO.getLastName());
        student.setEmail(studentRequestDTO.getEmail());

        List<CourseRequestDTO> courses = studentRequestDTO.getCourses();
        List<Course> courseList=new ArrayList<>();
        courses.forEach(courseRequestDTO -> {
            Course course=new Course();
            course.setName(courseRequestDTO.getName());
            courseList.add(course);
        });
        student.setCourses(courseList);
        return student;
    }

    private static StudentResponseDTO prepareStudentResponseInfo(Student student) {
       List<CourseResponseDTO> courseResponseDTOList =new ArrayList<>();
        List<Course> courses = student.getCourses();
        courses.forEach(course -> courseResponseDTOList.add(CourseResponseDTO.builder()
                .id(course.getId())
                .name(course.getName())
                .build()));

        return StudentResponseDTO.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .courses(courseResponseDTOList)
                .build();
    }
}
