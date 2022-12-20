package com.example.finally_project_boot.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthInfoResponse {

    private String email;
    private String token;
    private String role;

}
