package com.example.finally_project_boot.service.serviceImpl;

import com.example.finally_project_boot.config.jwt.JwtUtils;
import com.example.finally_project_boot.dto.request.AuthInfoRequest;
import com.example.finally_project_boot.dto.response.AuthInfoResponse;
import com.example.finally_project_boot.model.entity.Role;
import com.example.finally_project_boot.model.entity.User;
import com.example.finally_project_boot.repository.UserRepository;
import com.example.finally_project_boot.service.AuthInfoService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class AuthInfoServiceImpl implements AuthInfoService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;

    @Override
    public AuthInfoResponse returnToken(AuthInfoRequest authInfoRequest) {
        Authentication authentication;
        authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                authInfoRequest.getEmail(),
                authInfoRequest.getPassword()));

        String generatedToken = jwtUtils.generateToken(authentication);

        User userByEmail = userRepository.findByEmail(authInfoRequest.getEmail());
        List<Role> roles = userByEmail.getAuthInfo().getRoles();
        String role = String.valueOf(roles.get(0));


        return AuthInfoResponse.builder()
                .email(authInfoRequest.getEmail())
                .token(generatedToken)
                .role(role)
                .build();
    }
}