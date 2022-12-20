package com.example.finally_project_boot.model.mapper;

import com.example.finally_project_boot.dto.request.CourseRequest;
import com.example.finally_project_boot.dto.response.CourseResponse;
import com.example.finally_project_boot.model.entity.Course;
import com.example.finally_project_boot.repository.CourseRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourseMapper {

    private final CourseRepository courseRepository;

    public CourseMapper(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
    public Course mapToEntity(Long id, CourseRequest courseRequest) {
        if (courseRequest == null) {
            return null;
        }
        Course course = new Course();
        course.setId(id);
        course.setCourseName(courseRequest.getCourseName());
        course.setDateOfStart(courseRequest.getDateOfStart());
        course.setDuration(courseRequest.getDuration());
        course.setDescription(courseRequest.getDescription());
        return course;
    }
    public CourseResponse mapToResponse(Course course) {
        CourseResponse courseResponse = new CourseResponse();
        courseResponse.setId(course.getId());
        courseResponse.setCourseName(course.getCourseName());
        courseResponse.setDateOfStart(course.getDateOfStart());
        courseResponse.setDescription(course.getDescription());
        courseResponse.setDuration(course.getDuration());
        return courseResponse;
    }

//    public List<CourseResponse> mapToResponse(List<Course> courses) {
//        List<CourseResponse> responses = new ArrayList<>();
//        for (Course course : courses) {
//            responses.add(mapToResponse(course));
//        }
//        return responses;
//    }
//
//    public CourseResponse mapToResponse(Course course) {
//        if (course == null) {
//            return null;
//        }
//        CourseResponse courseResponse= new CourseResponse();
//        if (course.getId() != null) {
//            courseResponse.setId(course.getId());
//        }
//        courseResponse.setCourseName(course.getCourseName());
//        courseResponse.setDateOfStart(course.getDateOfStart());
//        courseResponse.setDuration(course.getDuration());
//        courseResponse.setDescription(course.getDescription());
//
//
//        return courseResponse;
//    }
}
