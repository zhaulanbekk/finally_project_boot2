package com.example.finally_project_boot.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoRequest {
    private String videoName;
    private String videoLink;
    private Long lessonId;
}
