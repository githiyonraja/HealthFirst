package com.health.HealthFirst.controller;

import com.health.HealthFirst.dto.JwtResponse;
import com.health.HealthFirst.dto.SignUpRequest;
import com.health.HealthFirst.security.JwtUtils;
import com.health.HealthFirst.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserServiceImpl service;
    private final JwtUtils jwtUtils;
    // PasswordEncoder injected previously; not required for signup response anymore

    public AuthController(UserServiceImpl service, JwtUtils jwtUtils) {
        this.service = service;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/signup")
    public JwtResponse signUp(@Valid @RequestBody SignUpRequest request){
        service.registerUser(request.getUsername(), request.getEmail(), request.getPassword());
        String token = jwtUtils.generateJwtToken(request.getUsername());
        return new JwtResponse(token, request.getUsername());
    }

    // @PostMapping("/login")
    // public JwtResponse login(@Valid @RequestBody LoginRequest request){
    //     User user = service.findByUserName(request.getUsername()).orElseThrow(()->new RuntimeException("User not found"));

    //     if (!passwordEncoder.matches(request.getPassword(), user.getPassword())){
    //         throw new RuntimeException("Invalid Password");
    //     }

    //     String token = jwtUtils.generateJwtToken(user.getUsername());
    //     return new JwtResponse(token, user.getUsername());
    // }

    @PostMapping("/logout")
    public String logout() {
        // In JWT, logout is handled client-side by deleting the token
        return "User logged out successfully (delete token on client side)";
    }

}
