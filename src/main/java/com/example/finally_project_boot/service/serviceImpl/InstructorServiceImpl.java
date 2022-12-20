package com.example.finally_project_boot.service.serviceImpl;

import com.example.finally_project_boot.dto.request.InstructorRequest;
import com.example.finally_project_boot.dto.response.InstructorResponse;
import com.example.finally_project_boot.model.entity.Company;
import com.example.finally_project_boot.model.entity.Instructor;
import com.example.finally_project_boot.model.mapper.InstructorMapper;
import com.example.finally_project_boot.repository.CompanyRepository;
import com.example.finally_project_boot.repository.InstructorRepository;
import com.example.finally_project_boot.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepository instructorRepository;
    private final CompanyRepository companyRepository;
    private final InstructorMapper instructorMapper;

    @Override
    public List<Instructor> getAllInstructors(Long companyId) {
        return instructorRepository.getAllById(companyId);
    }


    public List<Instructor> getInstructor(Long id) {
        return instructorRepository.getAllById(id);
    }

    @Override
    public InstructorResponse saveInstructor(InstructorRequest instructorRequest) {
        Instructor instructor = new Instructor();
        instructor.setFirstName(instructorRequest.getFirstName());
        instructor.setLastName(instructorRequest.getLastName());
        instructor.setPhoneNumber(instructorRequest.getPhoneNumber());
        instructor.setEmail(instructorRequest.getEmail());
        instructor.setSpecialization(instructorRequest.getSpecialization());

        Company company = companyRepository.findById(instructorRequest.getCompanyId()).get();
        company.addInst(instructor);
        instructor.setCompany(company);
        instructorRepository.save(instructor);
        return new InstructorResponse(instructor.getId(), instructor.getFirstName(),instructor.getLastName(),
                instructor.getPhoneNumber(),instructor.getEmail(),instructor.getSpecialization());
    }

    @Override
    public Optional<Instructor> findById(Long id) {
        boolean exist = instructorRepository.existsById(id);
        if (!exist) {
            throw new RuntimeException(
                    String.format("exist by id")
            );
        }
        return instructorRepository.findById(id);
    }

    @Override
    public InstructorResponse updateInstructor(Long id, InstructorRequest instructorRequest) {
        boolean exists = instructorRepository.existsById(id);
        Instructor instructor;
        if (!exists) {
            throw new RuntimeException(
                    String.format("update with id does not exists")
            );
        }
        instructor = instructorMapper.mapToEntity(id, instructorRequest);
        instructorRepository.save(instructor);
        return instructorMapper.mapToResponse(instructor);
    }

    @Override
    public InstructorResponse deleteById(Long id) {
       InstructorResponse instructorResponse= getById(id);
        boolean exist = instructorRepository.existsById(id);
        if (!exist) {
            throw new RuntimeException(
                    String.format("delete exist by id")
            );
        }
        instructorRepository.deleteById(id);
        return instructorResponse;
    }

    public InstructorResponse getById(Long courseId) {
        Instructor instructor = instructorRepository.findById(courseId).orElseThrow(
                () -> new NotFoundException(String.format("Course with id : %s courseId  is not found.", courseId)));
        return instructorMapper.mapToResponse(instructor);
    }
}
