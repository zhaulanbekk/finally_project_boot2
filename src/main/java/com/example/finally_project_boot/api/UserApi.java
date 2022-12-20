package com.example.finally_project_boot.api;

import com.example.finally_project_boot.dto.request.UserRequest;
import com.example.finally_project_boot.dto.response.UserResponse;
import com.example.finally_project_boot.model.entity.User;
import com.example.finally_project_boot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserApi {
    private final UserService userService;

    @GetMapping
    public List<UserResponse> findAll() {
        return userService.findAll();
    }

    @DeleteMapping("{id}")
    public void deleteBy(@PathVariable("id") Long id) {
        userService.deleteById(id);
    }

    @GetMapping("{id}")
    public Optional<User> findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PutMapping("{id}")
    public UserResponse update(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        return userService.update(id, userRequest);
    }
}