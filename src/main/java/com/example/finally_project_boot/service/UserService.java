package com.example.finally_project_boot.service;

import com.example.finally_project_boot.dto.request.UserRequest;
import com.example.finally_project_boot.dto.response.UserResponse;
import com.example.finally_project_boot.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    List<UserResponse> findAll();

    UserResponse registration(UserRequest userRequest);

    Optional<User> findById(Long id);

    UserResponse deleteById(Long id);

    UserResponse update(Long id, UserRequest userRequest);
}
