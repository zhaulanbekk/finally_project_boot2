package com.example.finally_project_boot.model.mapper;

import com.example.finally_project_boot.dto.request.LessonRequest;
import com.example.finally_project_boot.dto.response.LessonResponse;
import com.example.finally_project_boot.model.entity.Lesson;
import org.springframework.stereotype.Component;

@Component
public class LessonMapper {
    public Lesson mapToEntity(Long id, LessonRequest lessonRequest) {
        if (lessonRequest == null) {
            return null;
        }
        Lesson lesson = new Lesson();
        lesson.setId(id);
        lesson.setLessonName(lessonRequest.getLessonName());
        return lesson;
    }
    public LessonResponse mapToResponse(Lesson lesson) {
        LessonResponse lessonResponse = new LessonResponse();
        lessonResponse.setId(lesson.getId());
        lessonResponse.setLessonName(lesson.getLessonName());
           return lessonResponse;
    }
}
