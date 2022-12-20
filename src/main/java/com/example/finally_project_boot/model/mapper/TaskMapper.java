package com.example.finally_project_boot.model.mapper;

import com.example.finally_project_boot.dto.request.StudentRequest;
import com.example.finally_project_boot.dto.request.TaskRequest;
import com.example.finally_project_boot.dto.response.StudentResponse;
import com.example.finally_project_boot.dto.response.TaskResponse;
import com.example.finally_project_boot.model.entity.Student;
import com.example.finally_project_boot.model.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public Task mapToEntity(Long id, TaskRequest taskRequest) {
        if (taskRequest == null) {
            return null;
        }
        Task task = new Task();
        task.setId(taskRequest.getLessonId());
        task.setTaskName(taskRequest.getTaskName());
        task.setTaskText(taskRequest.getTaskText());
        task.setDeadline(taskRequest.getDeadline());
        return task;
    }

    public TaskResponse mapToResponse( Task task) {
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setId(task.getId());
        taskResponse.setTaskName(task.getTaskName());
        taskResponse.setTaskText(task.getTaskText());
        taskResponse.setDeadline(task.getDeadline());
        return taskResponse;
    }
}
