package com.example.finally_project_boot.repository;

import com.example.finally_project_boot.model.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
//    @Query("select l from Lesson l where l.course.id=:id")
    List<Lesson> getAllLessonById(Long id);
}