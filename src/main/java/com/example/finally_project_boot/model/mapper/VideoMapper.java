package com.example.finally_project_boot.model.mapper;

import com.example.finally_project_boot.dto.request.TaskRequest;
import com.example.finally_project_boot.dto.request.VideoRequest;
import com.example.finally_project_boot.dto.response.TaskResponse;
import com.example.finally_project_boot.dto.response.VideoResponse;
import com.example.finally_project_boot.model.entity.Task;
import com.example.finally_project_boot.model.entity.Video;
import org.springframework.stereotype.Component;

@Component
public class VideoMapper {

    public Video mapToEntity(Long id, VideoRequest videoRequest) {
        if (videoRequest == null) {
            return null;
        }

        Video video = new Video();
        video.setVideoName(videoRequest.getVideoName());
        video.setLink(videoRequest.getVideoLink());
        return video;
    }

    public VideoResponse  mapToResponse(Video video) {
        VideoResponse videoResponse = new VideoResponse();
        videoResponse.setId(video.getId());
        videoResponse.setVideoName(video.getVideoName());
        videoResponse.setVideoLink(video.getLink());
     return videoResponse;
    }
}
