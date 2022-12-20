package com.example.finally_project_boot.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String studyFormat;
    private Long companyId;
}
