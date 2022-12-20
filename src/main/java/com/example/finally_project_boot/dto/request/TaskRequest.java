package com.example.finally_project_boot.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
public class TaskRequest {
    private String taskName;
    private String taskText;
    private String deadline;
    private Long lessonId;
}
