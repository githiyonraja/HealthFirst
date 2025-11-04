package com.health.HealthFirst.controller;

import com.health.HealthFirst.dto.JwtResponse;
import com.health.HealthFirst.model.User;
import com.health.HealthFirst.security.JwtUtils;
import com.health.HealthFirst.service.UserServiceImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LegacyLoginController {
    private final UserServiceImpl userService;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public LegacyLoginController(UserServiceImpl userService, JwtUtils jwtUtils, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public JwtResponse login(@RequestParam("username") String username,
                             @RequestParam("password") String password) {
        User user = userService.findByUserName(username).orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }
        String token = jwtUtils.generateJwtToken(user.getUsername());
        return new JwtResponse(token, user.getUsername());
    }
}


