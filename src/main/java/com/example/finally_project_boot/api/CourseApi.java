package com.example.finally_project_boot.api;

import com.example.finally_project_boot.dto.request.CourseRequest;
import com.example.finally_project_boot.dto.response.CourseResponse;
import com.example.finally_project_boot.model.entity.Course;
import com.example.finally_project_boot.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
@PreAuthorize("hasAuthority('ADMIN')")

public class CourseApi {
    private final CourseService courseService;

    @GetMapping
    public List<Course> getAllCourses(Long companyId){
        return courseService.getAllCourses(companyId);
    }

    @PostMapping("/save")
    public CourseResponse saveCourse(@RequestBody CourseRequest courseRequest){
        return courseService.saveCourse(courseRequest);
    }

    @GetMapping("/{id}")
    public Optional<Course> getByIdCompany(@PathVariable Long id){
        return courseService.findById(id);
    }

    @DeleteMapping( "/delete/{id}")
    public CourseResponse deleteCompany(@PathVariable("id") Long id){
        return  courseService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public CourseResponse updateCompany(@PathVariable("id") Long id, @RequestBody CourseRequest courseRequest){
        return courseService.updateCourse(id,courseRequest);
    }


}
