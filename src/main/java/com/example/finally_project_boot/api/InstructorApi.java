package com.example.finally_project_boot.api;

import com.example.finally_project_boot.dto.request.InstructorRequest;
import com.example.finally_project_boot.dto.response.InstructorResponse;
import com.example.finally_project_boot.model.entity.Instructor;
import com.example.finally_project_boot.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/instructors")
@PreAuthorize("hasAuthority('ADMIN')")
public class InstructorApi {
    private final InstructorService instructorService;

    @GetMapping("/all/{id}")
    public List<Instructor> getAllInstructors(@PathVariable("id") Long companyId){
        return instructorService.getAllInstructors(companyId);
    }

    @PostMapping("/save")
    public InstructorResponse saveInstructor(@RequestBody InstructorRequest instructorRequest){
        return instructorService.saveInstructor(instructorRequest);
    }

    @GetMapping("/{id}")
    public Optional<Instructor> getById(@PathVariable Long id){
        return instructorService.findById(id);
    }



    @DeleteMapping( "/delete/{id}")
    public InstructorResponse deleteInstructor(@PathVariable("id") Long id){
        return  instructorService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public InstructorResponse updateInstructor(@PathVariable("id") Long id, @RequestBody InstructorRequest instructorRequest){
        return instructorService.updateInstructor(id,instructorRequest);
    }


}
