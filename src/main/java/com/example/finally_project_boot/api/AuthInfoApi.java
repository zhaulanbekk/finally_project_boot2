package com.example.finally_project_boot.api;

import com.example.finally_project_boot.dto.request.AuthInfoRequest;
import com.example.finally_project_boot.dto.request.UserRequest;
import com.example.finally_project_boot.dto.response.AuthInfoResponse;
import com.example.finally_project_boot.dto.response.UserResponse;
import com.example.finally_project_boot.service.AuthInfoService;
import com.example.finally_project_boot.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping("api/authInfo")
public class AuthInfoApi {
    private final AuthInfoService authInfoService;
    private final UserService userService;

    @PostMapping("/login")
    public AuthInfoResponse save(@RequestBody AuthInfoRequest authInfoRequest) {
        return authInfoService.returnToken(authInfoRequest);
    }

    @PostMapping("/registration")
    public UserResponse registration(@RequestBody UserRequest userRequest) {
        return userService.registration(userRequest);
    }
}
