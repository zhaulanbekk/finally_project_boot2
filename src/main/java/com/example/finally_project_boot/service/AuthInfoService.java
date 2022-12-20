package com.example.finally_project_boot.service;

import com.example.finally_project_boot.dto.request.AuthInfoRequest;
import com.example.finally_project_boot.dto.response.AuthInfoResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthInfoService {
    AuthInfoResponse returnToken(AuthInfoRequest authInfoRequest);
}
