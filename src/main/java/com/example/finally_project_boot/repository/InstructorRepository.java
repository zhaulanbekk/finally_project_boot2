package com.example.finally_project_boot.repository;

import com.example.finally_project_boot.model.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    @Query("select i from Instructor i where i.company.id=:id")
    List<Instructor> getAllById(Long id);

}