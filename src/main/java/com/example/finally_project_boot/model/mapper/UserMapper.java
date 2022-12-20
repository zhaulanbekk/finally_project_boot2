package com.example.finally_project_boot.model.mapper;

import com.example.finally_project_boot.dto.request.UserRequest;
import com.example.finally_project_boot.dto.response.UserResponse;
import com.example.finally_project_boot.model.entity.AuthInfo;
import com.example.finally_project_boot.model.entity.User;
import com.example.finally_project_boot.repository.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    private final RoleRepository roleRepository;

    public UserMapper(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public User mapToEntity(Long id, UserRequest userRequest) {
        if (userRequest == null) {
            return null;
        }
        User user = new User();
        user.setId(id);
        user.setUserName(userRequest.getUserName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());

        AuthInfo authInfo = new AuthInfo();
        authInfo.setEmail(userRequest.getEmail());
        authInfo.setPassword(user.getPassword());
        authInfo.setAuthInfo1(roleRepository.getByName("STUDENT"));
        user.setAuthInfo(authInfo);
        return user;
    }

    public List<UserResponse> mapToResponse(List<User> userList) {
        List<UserResponse> responses = new ArrayList<>();
        for (User user : userList) {
            responses.add(mapToResponse(user));
        }
        return responses;
    }

    public UserResponse mapToResponse(User user) {
        if (user == null) {
            return null;
        }
        UserResponse userResponse = new UserResponse();
        if (user.getId() != null) {
            userResponse.setId(String.valueOf(user.getId()));
        }
        userResponse.setUserName(user.getUserName());
        userResponse.setEmail(user.getEmail());
        return userResponse;

    }
}