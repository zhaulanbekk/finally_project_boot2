package com.example.finally_project_boot.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
public class UserRequest {

    @NotBlank
    private String userName;
    @Email
    private String email;
    private String password;
}
