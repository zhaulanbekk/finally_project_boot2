package com.example.finally_project_boot.repository;

import com.example.finally_project_boot.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("select t from Task t where t.lesson.id=:id")
    List<Task> getAllTaskById(Long id);
}