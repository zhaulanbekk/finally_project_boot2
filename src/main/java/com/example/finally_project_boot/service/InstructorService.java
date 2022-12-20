package com.example.finally_project_boot.service;

import com.example.finally_project_boot.dto.request.InstructorRequest;
import com.example.finally_project_boot.dto.response.InstructorResponse;
import com.example.finally_project_boot.model.entity.Instructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface InstructorService {
    List<Instructor> getAllInstructors(Long companyId);

    InstructorResponse saveInstructor(InstructorRequest instructorRequest);

    Optional<Instructor> findById(Long id);

    InstructorResponse updateInstructor(Long id, InstructorRequest instructorRequest);

    InstructorResponse deleteById(Long id);
}
