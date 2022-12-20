package com.example.finally_project_boot.repository;

import com.example.finally_project_boot.model.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    @Query("select v from Video v where v.lesson.id=:id")
    List<Video> getAllVideoById(Long id);
}