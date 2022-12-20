package com.example.finally_project_boot.model.mapper;

import com.example.finally_project_boot.dto.request.InstructorRequest;
import com.example.finally_project_boot.dto.response.InstructorResponse;
import com.example.finally_project_boot.model.entity.Instructor;
import org.springframework.stereotype.Component;

@Component
public class InstructorMapper {
    public Instructor mapToEntity(Long id, InstructorRequest instructorRequest) {
        if (instructorRequest == null) {
            return null;
        }
        Instructor instructor = new Instructor();
        instructor.setId(id);
        instructor.setFirstName(instructorRequest.getFirstName());
        instructor.setLastName(instructorRequest.getLastName());
        instructor.setPhoneNumber(instructorRequest.getPhoneNumber());
        instructor.setEmail(instructorRequest.getEmail());
        instructor.setSpecialization(instructorRequest.getSpecialization());
        return instructor;
    }
    public InstructorResponse mapToResponse(Instructor instructor) {
        InstructorResponse instructorResponse = new InstructorResponse();
        instructorResponse.setId(instructor.getId());
        instructorResponse.setFirstName(instructor.getFirstName());
        instructorResponse.setLastName(instructor.getLastName());
        instructorResponse.setPhoneNumber(instructor.getPhoneNumber());
        instructorResponse.setEmail(instructor.getEmail());
        instructorResponse.setSpecialization(instructor.getSpecialization());

        return instructorResponse;
    }
}
