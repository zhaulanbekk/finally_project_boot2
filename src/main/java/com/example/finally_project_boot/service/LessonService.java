package com.example.finally_project_boot.service;

import com.example.finally_project_boot.dto.request.LessonRequest;
import com.example.finally_project_boot.dto.response.LessonResponse;
import com.example.finally_project_boot.model.entity.Lesson;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface LessonService {

    List<Lesson> getAllLessons(Long courseId);

    LessonResponse saveLesson(LessonRequest lessonRequest);

    Optional<Lesson> findById(Long id);

    LessonResponse updateLesson(Long id, LessonRequest lessonRequest);

    LessonResponse deleteById(Long id);
}
