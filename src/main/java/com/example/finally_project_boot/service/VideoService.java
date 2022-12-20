package com.example.finally_project_boot.service;

import com.example.finally_project_boot.dto.request.VideoRequest;
import com.example.finally_project_boot.dto.response.VideoResponse;
import com.example.finally_project_boot.model.entity.Video;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface VideoService {

    List<Video> getAllVideos(Long lessonId);

    VideoResponse saveVideo(VideoRequest videoRequest);

    Optional<Video> findById(Long id);

    VideoResponse updateVideo(Long id, VideoRequest videoRequest);

    VideoResponse deleteById(Long id);

}
