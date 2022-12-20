package com.example.finally_project_boot.service.serviceImpl;

import com.example.finally_project_boot.dto.request.TaskRequest;
import com.example.finally_project_boot.dto.response.TaskResponse;
import com.example.finally_project_boot.model.entity.Lesson;
import com.example.finally_project_boot.model.entity.Task;
import com.example.finally_project_boot.model.mapper.TaskMapper;
import com.example.finally_project_boot.repository.LessonRepository;
import com.example.finally_project_boot.repository.TaskRepository;
import com.example.finally_project_boot.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final LessonRepository lessonRepository;
    private final TaskMapper taskMapper;


    @Override
    public List<Task> getAllTask(Long lessonId) {
        return taskRepository.getAllTaskById(lessonId);
    }

    @Override
    public TaskResponse saveTask(TaskRequest taskRequest) {
        Task task = new Task();
        task.setTaskName(taskRequest.getTaskName());
        task.setTaskText(taskRequest.getTaskText());
        task.setDeadline(taskRequest.getDeadline());


        Lesson lesson = lessonRepository.findById(taskRequest.getLessonId()).get();
        lesson.addTask(task);
        task.setLesson(lesson);
        taskRepository.save(task);
        return new TaskResponse(task.getId(), task.getTaskName(), task.getTaskText(), task.getDeadline());
    }

    @Override
    public Optional<Task> findById(Long id) {
        boolean exist = taskRepository.existsById(id);
        if (!exist) {
            throw new RuntimeException(
                    String.format("exist by id")
            );
        }
        return taskRepository.findById(id);
    }

    @Override
    public TaskResponse updateTask(Long id, TaskRequest taskRequest) {
        boolean exists = taskRepository.existsById(id);
        Task task;
        if (!exists) {
            throw new RuntimeException(
                    String.format("update with id does not exists")
            );
        }
        task = taskMapper.mapToEntity(id, taskRequest);
        taskRepository.save(task);
        return taskMapper.mapToResponse(task);
    }

    @Override
    public TaskResponse deleteById(Long id) {
        TaskResponse taskResponse= getById(id);
        boolean exist = lessonRepository.existsById(id);
        if (!exist) {
            throw new RuntimeException(
                    String.format("delete exist by id")
            );
        }
        taskRepository.deleteById(id);
        return taskResponse;
    }

    public TaskResponse getById(Long courseId) {
        Task task = taskRepository.findById(courseId).orElseThrow(
                () -> new NotFoundException(String.format("Course with id : %s courseId  is not found.", courseId)));
        return taskMapper.mapToResponse(task);
    }
}
