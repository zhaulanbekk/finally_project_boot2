package com.example.finally_project_boot.service;

import com.example.finally_project_boot.dto.request.CourseRequest;
import com.example.finally_project_boot.dto.response.CourseResponse;
import com.example.finally_project_boot.model.entity.Course;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CourseService {

    List<Course> getAllCourses(Long companyId);

    CourseResponse saveCourse(CourseRequest courseRequest);

    Optional<Course> findById(Long id);

    CourseResponse updateCourse(Long id, CourseRequest courseRequest);

    CourseResponse deleteById(Long id);
}
