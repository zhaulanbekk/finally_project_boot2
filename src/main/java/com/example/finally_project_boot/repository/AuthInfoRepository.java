package com.example.finally_project_boot.repository;

import com.example.finally_project_boot.model.entity.AuthInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthInfoRepository extends JpaRepository<AuthInfo,Long> {
    Optional<AuthInfo> findByEmail(String email);
}
