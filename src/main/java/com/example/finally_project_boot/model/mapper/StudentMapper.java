package com.example.finally_project_boot.model.mapper;

import com.example.finally_project_boot.dto.request.StudentRequest;
import com.example.finally_project_boot.dto.response.StudentResponse;
import com.example.finally_project_boot.model.entity.Company;
import com.example.finally_project_boot.model.entity.Student;
import com.example.finally_project_boot.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentMapper {

    private final CompanyRepository companyRepository;

    public Student mapToEntity(Long id, StudentRequest studentRequest) {
        if (studentRequest == null) {
            return null;
        }
        Student student = new Student();
        student.setId(id);
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setEmail(studentRequest.getEmail());
        student.setStudyFormat(studentRequest.getStudyFormat());
        Company company = companyRepository.findById(studentRequest.getCompanyId()).get();
        student.setCompany(company);
        return student;
    }

    public StudentResponse mapToResponse(Student student) {
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setId(student.getId());
        studentResponse.setFirstName(student.getFirstName());
        studentResponse.setLastName(student.getLastName());
        studentResponse.setEmail(student.getEmail());
        studentResponse.setStudyFormat(student.getStudyFormat());
        return studentResponse;
    }
}
