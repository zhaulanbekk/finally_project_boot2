package com.example.finally_project_boot.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequest {
    private String courseName;
    private LocalDate dateOfStart;
    private int duration;
    private String description;
    private Long companyId;
}
