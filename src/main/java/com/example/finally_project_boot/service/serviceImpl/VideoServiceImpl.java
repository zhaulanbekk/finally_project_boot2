package com.example.finally_project_boot.service.serviceImpl;

import com.example.finally_project_boot.dto.request.VideoRequest;
import com.example.finally_project_boot.dto.response.TaskResponse;
import com.example.finally_project_boot.dto.response.VideoResponse;
import com.example.finally_project_boot.model.entity.Lesson;
import com.example.finally_project_boot.model.entity.Task;
import com.example.finally_project_boot.model.entity.Video;
import com.example.finally_project_boot.model.mapper.VideoMapper;
import com.example.finally_project_boot.repository.LessonRepository;
import com.example.finally_project_boot.repository.VideoRepository;
import com.example.finally_project_boot.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class VideoServiceImpl implements VideoService {
    private final VideoRepository videoRepository;
    private final LessonRepository lessonRepository;
    private final VideoMapper videoMapper;

    @Override
    public List<Video> getAllVideos(Long lessonId) {
        return videoRepository.getAllVideoById(lessonId);
    }

    @Override
    public VideoResponse saveVideo(VideoRequest videoRequest) {
        Video video = new Video();
        video.setVideoName(videoRequest.getVideoName());
        video.setLink(videoRequest.getVideoLink());



        Lesson lesson = lessonRepository.findById(videoRequest.getLessonId()).get();
        lesson.setVideo(video);
        video.setLesson(lesson);
        videoRepository.save(video);
        return new VideoResponse(video.getId(),video.getVideoName(),video.getLink());
    }

    @Override
    public Optional<Video> findById(Long id) {
        boolean exist = videoRepository.existsById(id);
        if (!exist) {
            throw new RuntimeException(
                    String.format("exist by id")
            );
        }
        return videoRepository.findById(id);
    }

    @Override
    public VideoResponse updateVideo(Long id, VideoRequest videoRequest) {
        boolean exists = videoRepository.existsById(id);
        Video video;
        if (!exists) {
            throw new RuntimeException(
                    String.format("update with id does not exists")
            );
        }
        video = videoMapper.mapToEntity(id, videoRequest);
        videoRepository.save(video);
        return videoMapper.mapToResponse(video);
    }

    @Override
    public VideoResponse deleteById(Long id) {
        VideoResponse videoResponse= getById(id);
        boolean exist = lessonRepository.existsById(id);
        if (!exist) {
            throw new RuntimeException(
                    String.format("delete exist by id")
            );
        }
        videoRepository.deleteById(id);
        return videoResponse;
    }

    public VideoResponse getById(Long courseId) {
        Video video = videoRepository.findById(courseId).orElseThrow(
                () -> new NotFoundException(String.format("Course with id : %s courseId  is not found.", courseId)));
        return videoMapper.mapToResponse(video);
    }
}
