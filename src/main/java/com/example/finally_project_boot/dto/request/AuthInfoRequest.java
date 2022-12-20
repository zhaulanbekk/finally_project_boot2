package com.example.finally_project_boot.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthInfoRequest {

    private String email;

    private String password;
}
