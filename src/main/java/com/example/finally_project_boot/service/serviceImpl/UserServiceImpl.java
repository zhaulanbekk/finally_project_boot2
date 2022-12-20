package com.example.finally_project_boot.service.serviceImpl;

import com.example.finally_project_boot.dto.request.UserRequest;
import com.example.finally_project_boot.dto.response.UserResponse;
import com.example.finally_project_boot.model.entity.User;
import com.example.finally_project_boot.model.mapper.UserMapper;
import com.example.finally_project_boot.repository.UserRepository;
import com.example.finally_project_boot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserResponse> findAll() {
        return userMapper.mapToResponse(userRepository.findAll());
    }

    @Override
    public UserResponse registration(UserRequest userRequest) {

        String encodedPassword = passwordEncoder.encode(userRequest.getPassword());
        userRequest.setPassword(encodedPassword);


        User user = userMapper.mapToEntity(null, userRequest);
        User save = userRepository.save(user);
        return userMapper.mapToResponse(save);
    }

    @Override
    public Optional<User> findById(Long id) {
        boolean exists = userRepository.existsById(id);
        if (!exists) {
            throw new RuntimeException(String.format(" %d not", id));
        }
        return userRepository.findById(id);
    }

    @Override
    public UserResponse deleteById(Long id) {
        UserResponse userResponse = getById(id);
        boolean exists = userRepository.existsById(id);
        if (!exists) {
            throw new RuntimeException(
                    String.format("%s  exists", id)
            );
        }
        userRepository.deleteById(id);
        return userResponse;
    }

    @Override
    public UserResponse update(Long id, UserRequest userRequest) {
        boolean exists = userRepository.existsById(id);
        User response;
        if (!exists) {
            throw new RuntimeException(
                    String.format(" %d  exists", id));
        } else {
            String encodedPassword = passwordEncoder.encode(userRequest.getPassword());
            userRequest.setPassword(encodedPassword);

            response = userMapper.mapToEntity(id, userRequest);
            userRepository.save(response);
        }
        return userMapper.mapToResponse(response);

    }

    private UserResponse getById(Long id) {
        return userMapper.mapToResponse(userRepository.findById(id).orElseThrow(() -> new RuntimeException(
                String.format(" %s  exists", id)
        )));
    }
}