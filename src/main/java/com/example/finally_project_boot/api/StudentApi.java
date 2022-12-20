package com.example.finally_project_boot.api;

import com.example.finally_project_boot.dto.request.StudentRequest;
import com.example.finally_project_boot.dto.response.StudentResponse;
import com.example.finally_project_boot.model.entity.Student;
import com.example.finally_project_boot.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
@PreAuthorize("hasAuthority('ADMIN')")

public class StudentApi {

    private final StudentService studentService;

    @GetMapping("/all/{id}")
    public List<Student> getAllStudents(@PathVariable("id") Long companyId) {
        return studentService.getAllStudents(companyId);
    }

    @PostMapping("/save")
    public StudentResponse saveStudent(@RequestBody StudentRequest studentRequest) {
        return studentService.saveStudent(studentRequest);
    }

    @GetMapping("/{id}")
    public Optional<Student> getById(@PathVariable Long id) {
        return studentService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public StudentResponse deleteStudent(@PathVariable("id") Long id) {
        return studentService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public StudentResponse updateStudent(@PathVariable("id") Long id, @RequestBody StudentRequest studentRequest) {
        return studentService.updateStudent(id, studentRequest);
    }

}
