package com.example.finally_project_boot.service.serviceImpl;

import com.example.finally_project_boot.dto.request.StudentRequest;
import com.example.finally_project_boot.dto.response.StudentResponse;
import com.example.finally_project_boot.model.entity.Company;
import com.example.finally_project_boot.model.entity.Student;
import com.example.finally_project_boot.model.mapper.StudentMapper;
import com.example.finally_project_boot.repository.CompanyRepository;
import com.example.finally_project_boot.repository.StudentRepository;
import com.example.finally_project_boot.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final CompanyRepository companyRepository;
    private final StudentMapper studentMapper;


    @Override
    public List<Student> getAllStudents(Long companyId) {
        return studentRepository.getAllStudentById(companyId);
    }

    @Override
    public StudentResponse saveStudent(StudentRequest studentRequest) {
        Student student = new Student();
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setEmail(studentRequest.getEmail());
        student.setStudyFormat(studentRequest.getStudyFormat());

        Company company = companyRepository.findById(studentRequest.getCompanyId()).get();
        company.addStudent(student);
        student.setCompany(company);
        studentRepository.save(student);
        return new StudentResponse(student.getId(),student.getFirstName(),
                student.getLastName(),student.getEmail(),student.getStudyFormat());
    }

    @Override
    public Optional<Student> findById(Long id) {
        boolean exist = studentRepository.existsById(id);
        if (!exist) {
            throw new RuntimeException(
                    String.format("exist by id")
            );
        }
        return studentRepository.findById(id);
    }

    @Override
    public StudentResponse updateStudent(Long id, StudentRequest studentRequest) {
        boolean exists = studentRepository.existsById(id);
        Student student;
        if (!exists) {
            throw new RuntimeException(
                    String.format("update with id does not exists")
            );
        }
//        Student student1 = studentRepository.findById(id).get();
//        student1.setFirstName(studentRequest.getFirstName());
//        student1.setLastName(studentRequest.getLastName());
//        student1.setEmail(studentRequest.getEmail());
//        student1.setStudyFormat(studentRequest.getStudyFormat());
//        Company company = companyRepository.findById(studentRequest.getCompanyId()).get();
//        student1.setCompany(company);
//
//        studentRepository.save(student1);
//
//        StudentResponse response = new StudentResponse();
//        response.setId(student1.getId());
//        response.setFirstName(student1.getFirstName());
//        response.setLastName(student1.getLastName());
//        response.setEmail(student1.getEmail());
//        response.setStudyFormat(student1.getStudyFormat());

//        return response;
        student = studentMapper.mapToEntity(id, studentRequest);
        studentRepository.save(student);
        return studentMapper.mapToResponse(student);
    }

    @Override
    public StudentResponse deleteById(Long id) {
        StudentResponse studentResponse= getById(id);
        boolean exist = studentRepository.existsById(id);
        if (!exist) {
            throw new RuntimeException(
                    String.format("delete exist by id")
            );
        }
        studentRepository.deleteById(id);
        return studentResponse;
    }

    public StudentResponse getById(Long courseId) {
        Student student = studentRepository.findById(courseId).orElseThrow(
                () -> new NotFoundException(String.format("Course with id : %s courseId  is not found.", courseId)));
        return studentMapper.mapToResponse(student);
    }
}
