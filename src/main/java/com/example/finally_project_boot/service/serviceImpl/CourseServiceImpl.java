package com.example.finally_project_boot.service.serviceImpl;

import com.example.finally_project_boot.dto.request.CourseRequest;
import com.example.finally_project_boot.dto.response.CourseResponse;
import com.example.finally_project_boot.model.entity.Company;
import com.example.finally_project_boot.model.entity.Course;
import com.example.finally_project_boot.model.mapper.CourseMapper;
import com.example.finally_project_boot.repository.CompanyRepository;
import com.example.finally_project_boot.repository.CourseRepository;
import com.example.finally_project_boot.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final CompanyRepository companyRepository;
    private final CourseMapper courseMapper;


    @Override
    public List<Course> getAllCourses(Long companyId) {
        return courseRepository.getAllByCompanyId(companyId);
    }

    @Override
    public CourseResponse saveCourse(CourseRequest courseRequest) {
        Course course = new Course();
        course.setCourseName(courseRequest.getCourseName());
        course.setDateOfStart(courseRequest.getDateOfStart());
        course.setDuration(courseRequest.getDuration());
        course.setDescription(courseRequest.getDescription());
        Company company = companyRepository.findById(courseRequest.getCompanyId()).get();
        company.addCourse(course);
        course.setCompany(company);
        courseRepository.save(course);
        return new CourseResponse(course.getId(), course.getCourseName(), course.getDateOfStart(),
                course.getDuration(), course.getDescription());
    }

    @Override
    public Optional<Course> findById(Long id) {
        boolean exist = courseRepository.existsById(id);
        if (!exist) {
            throw new RuntimeException(
                    String.format("exist by id")
            );
        }
        return courseRepository.findById(id);
    }

    @Override
    public CourseResponse updateCourse(Long id, CourseRequest courseRequest) {
        boolean exists = courseRepository.existsById(id);
        Course course;
        if (!exists) {
            throw new RuntimeException(
                    String.format("update with id does not exists")
            );
        }
        course = courseMapper.mapToEntity(id, courseRequest);
        courseRepository.save(course);
        return courseMapper.mapToResponse(course);
    }

    @Override
    public CourseResponse deleteById(Long id) {
        CourseResponse courseResponse = getById(id);
        boolean exist = courseRepository.existsById(id);
        if (!exist) {
            throw new RuntimeException(
                    String.format("delete exist by id")
            );
        }
        courseRepository.deleteById(id);
        return courseResponse;
    }

    public CourseResponse getById(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(
                () -> new NotFoundException(String.format("Course with id : %s courseId  is not found.", courseId)));
        return courseMapper.mapToResponse(course);
    }
}

