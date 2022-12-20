package com.example.finally_project_boot.service;

import com.example.finally_project_boot.dto.request.InstructorRequest;
import com.example.finally_project_boot.dto.request.StudentRequest;
import com.example.finally_project_boot.dto.response.InstructorResponse;
import com.example.finally_project_boot.dto.response.StudentResponse;
import com.example.finally_project_boot.model.entity.Instructor;
import com.example.finally_project_boot.model.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface StudentService {

    List<Student> getAllStudents(Long companyId);

    StudentResponse saveStudent(StudentRequest studentRequest);

    Optional<Student> findById(Long id);

    StudentResponse updateStudent(Long id, StudentRequest studentRequest);

    StudentResponse deleteById(Long id);
}
