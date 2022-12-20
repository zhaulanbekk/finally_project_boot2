package com.example.finally_project_boot.repository;

import com.example.finally_project_boot.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("select s from Student s where s.company.id=:id")
    List<Student> getAllStudentById(Long id);
}