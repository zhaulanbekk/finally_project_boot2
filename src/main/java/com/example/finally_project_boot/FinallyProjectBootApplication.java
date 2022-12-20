package com.example.finally_project_boot;

import com.example.finally_project_boot.model.entity.AuthInfo;
import com.example.finally_project_boot.model.entity.Role;
import com.example.finally_project_boot.model.entity.User;
import com.example.finally_project_boot.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@SpringBootApplication
@AllArgsConstructor
@RestController
public class FinallyProjectBootApplication {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(FinallyProjectBootApplication.class, args);
    }
    @PostConstruct
    public void init() {
        User user = new User();
        user.setUserName("Admin");
        user.setEmail("admin@gmail.com");
        user.setPassword(passwordEncoder.encode("admin"));


        Role role = new Role();
        Role role1 = new Role();
        role.setName("ADMIN");
        role1.setName("STUDENT");

        AuthInfo authInfo = new AuthInfo();
        authInfo.setEmail(user.getEmail());
        authInfo.setPassword(user.getPassword());


        authInfo.setAuthInfo1(role);
        authInfo.setAuthInfo1(role1);

        user.setAuthInfo(authInfo);
        userRepository.save(user);

    }

}
