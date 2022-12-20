package com.example.finally_project_boot.service;

import com.example.finally_project_boot.dto.request.TaskRequest;
import com.example.finally_project_boot.dto.response.TaskResponse;
import com.example.finally_project_boot.model.entity.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface TaskService {

    List<Task> getAllTask(Long lessonId);

    TaskResponse saveTask(TaskRequest taskRequest);

    Optional<Task> findById(Long id);

    TaskResponse updateTask(Long id, TaskRequest taskRequest);

    TaskResponse deleteById(Long id);
}
