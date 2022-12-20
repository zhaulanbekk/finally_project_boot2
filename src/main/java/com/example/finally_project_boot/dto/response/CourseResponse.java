package com.example.finally_project_boot.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
public class CourseResponse {
    private Long id;
    private String courseName;
    private LocalDate dateOfStart;
    private int duration;
    private String description;

    public CourseResponse(Long id, String courseName, LocalDate dateOfStart, int duration, String description) {
        this.id = id;
        this.courseName = courseName;
        this.dateOfStart = dateOfStart;
        this.duration = duration;
        this.description = description;
    }


}
