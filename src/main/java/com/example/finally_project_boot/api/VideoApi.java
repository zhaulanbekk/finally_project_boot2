package com.example.finally_project_boot.api;

import com.example.finally_project_boot.dto.request.VideoRequest;
import com.example.finally_project_boot.dto.response.VideoResponse;
import com.example.finally_project_boot.model.entity.Video;
import com.example.finally_project_boot.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/videos")
@PreAuthorize("hasAuthority('STUDENT')")

public class VideoApi {
    private final VideoService videoService;

    @GetMapping("/all/{id}")
    public List<Video> getAllVideo(@PathVariable("id") Long lessonId){
        return videoService.getAllVideos(lessonId);
    }

    @PostMapping("/save")
    public VideoResponse saveVideo(@RequestBody VideoRequest videoRequest){
        return videoService.saveVideo(videoRequest);
    }

    @GetMapping("/{id}")
    public Optional<Video> getById(@PathVariable Long id){
        return videoService.findById(id);
    }



    @DeleteMapping( "/delete/{id}")
    public VideoResponse deleteVideo(@PathVariable("id") Long id){
        return  videoService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public VideoResponse updateTask(@PathVariable("id") Long id, @RequestBody VideoRequest videoRequest){
        return videoService.updateVideo(id,videoRequest);
    }
}
