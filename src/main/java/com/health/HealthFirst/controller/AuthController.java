package com.health.HealthFirst.controller;

import com.health.HealthFirst.dto.JwtResponse;
import com.health.HealthFirst.dto.LoginRequest;
import com.health.HealthFirst.dto.SignUpRequest;
import com.health.HealthFirst.model.User;
import com.health.HealthFirst.security.JwtUtils;
import com.health.HealthFirst.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserServiceImpl service;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserServiceImpl service, JwtUtils jwtUtils, PasswordEncoder passwordEncoder) {
        this.service = service;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signup")
    public String signUp(@Valid @RequestBody SignUpRequest request){
        service.registerUser(request.getUsername(),request.getPassword(),request.getEmail());
        return "User registered Successfully!";
    }

    @PostMapping("/login")
    public JwtResponse login(@Valid @RequestBody LoginRequest request){
        User user = service.findByUserName(request.getUsername()).orElseThrow(()->new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid Password");
        }

        String token = jwtUtils.generateJwtToken(user.getUsername());
        return new JwtResponse(token, user.getUsername());
    }

    @PostMapping("/logout")
    public String logout() {
        // In JWT, logout is handled client-side by deleting the token
        return "User logged out successfully (delete token on client side)";
    }

}
