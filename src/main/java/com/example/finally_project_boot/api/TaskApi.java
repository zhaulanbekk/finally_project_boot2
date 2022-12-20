package com.example.finally_project_boot.api;

import com.example.finally_project_boot.dto.request.TaskRequest;
import com.example.finally_project_boot.dto.response.TaskResponse;
import com.example.finally_project_boot.model.entity.Task;
import com.example.finally_project_boot.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
@PreAuthorize("hasAuthority('STUDENT')")

public class TaskApi {

    private final TaskService taskService;


    @GetMapping("/all/{id}")
    public List<Task> getAllTasks(@PathVariable("id") Long companyId){
        return taskService.getAllTask(companyId);
    }

    @PostMapping("/save")
    public TaskResponse saveTask(@RequestBody TaskRequest taskRequest){
        return taskService.saveTask(taskRequest);
    }

    @GetMapping("/{id}")
    public Optional<Task> getById(@PathVariable Long id){
        return taskService.findById(id);
    }



    @DeleteMapping( "/delete/{id}")
    public TaskResponse deleteTask(@PathVariable("id") Long id){
        return  taskService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public TaskResponse updateTask(@PathVariable("id") Long id, @RequestBody TaskRequest taskRequest){
        return taskService.updateTask(id,taskRequest);
    }
}
