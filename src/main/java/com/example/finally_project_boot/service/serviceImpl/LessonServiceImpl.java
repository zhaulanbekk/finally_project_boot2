package com.example.finally_project_boot.service.serviceImpl;

import com.example.finally_project_boot.dto.request.LessonRequest;
import com.example.finally_project_boot.dto.response.LessonResponse;
import com.example.finally_project_boot.model.entity.Course;
import com.example.finally_project_boot.model.entity.Lesson;
import com.example.finally_project_boot.model.mapper.LessonMapper;
import com.example.finally_project_boot.repository.CourseRepository;
import com.example.finally_project_boot.repository.LessonRepository;
import com.example.finally_project_boot.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;
    private final LessonMapper lessonMapper;

    @Override
    public List<Lesson> getAllLessons(Long courseId) {
        return lessonRepository.getAllLessonById(courseId);
    }

    @Override
    public LessonResponse saveLesson(LessonRequest lessonRequest) {
        Lesson lesson = new Lesson();
        lesson.setLessonName(lessonRequest.getLessonName());


        Course course = courseRepository.findById(lessonRequest.getCourseId()).get();
        course.addLesson(lesson);
        lesson.setCourse(course);
        lessonRepository.save(lesson);
        return new LessonResponse(lesson.getId(),lesson.getLessonName());
    }

    @Override
    public Optional<Lesson> findById(Long id) {
        boolean exist = lessonRepository.existsById(id);
        if (!exist) {
            throw new RuntimeException(
                    String.format("exist by id")
            );
        }
        return lessonRepository.findById(id);
    }

    @Override
    public LessonResponse updateLesson(Long id, LessonRequest lessonRequest) {
        boolean exists = lessonRepository.existsById(id);
        Lesson lesson;
        if (!exists) {
            throw new RuntimeException(
                    String.format("update with id does not exists")
            );
        }
        lesson = lessonMapper.mapToEntity(id, lessonRequest);
        lessonRepository.save(lesson);
        return lessonMapper.mapToResponse(lesson);
    }

    @Override
    public LessonResponse deleteById(Long id) {
        LessonResponse lessonResponse= getById(id);
        boolean exist = lessonRepository.existsById(id);
        if (!exist) {
            throw new RuntimeException(
                    String.format("delete exist by id")
            );
        }
        lessonRepository.deleteById(id);
        return lessonResponse;
    }

    private LessonResponse getById(Long courseId) {
        Lesson lesson = lessonRepository.findById(courseId).orElseThrow(
                () -> new NotFoundException(String.format("Course with id : %s courseId  is not found.", courseId)));
        return lessonMapper.mapToResponse(lesson);
    }
}
