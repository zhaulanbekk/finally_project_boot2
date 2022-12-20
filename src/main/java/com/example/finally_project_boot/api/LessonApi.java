package com.example.finally_project_boot.api;

import com.example.finally_project_boot.dto.request.LessonRequest;
import com.example.finally_project_boot.dto.response.LessonResponse;
import com.example.finally_project_boot.model.entity.Lesson;
import com.example.finally_project_boot.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lessons")
@PreAuthorize("hasAuthority('ADMIN')")

public class LessonApi {

    private final LessonService lessonService;

    @GetMapping("/all/{id}")
    public List<Lesson> getAllLessons(@PathVariable("id") Long courseId){
        return lessonService.getAllLessons(courseId);
    }

    @PostMapping("/save")
    public LessonResponse saveLesson(@RequestBody LessonRequest lessonRequest){
        return lessonService.saveLesson(lessonRequest);
    }

    @GetMapping("/{id}")
    public Optional<Lesson> getById(@PathVariable Long id){
        return lessonService.findById(id);
    }

    @DeleteMapping( "/delete/{id}")
    public LessonResponse deleteLesson(@PathVariable("id") Long id){
        return  lessonService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public LessonResponse updateLesson(@PathVariable("id") Long id, @RequestBody LessonRequest lessonRequest ){
        return lessonService.updateLesson(id,lessonRequest);
    }
}
