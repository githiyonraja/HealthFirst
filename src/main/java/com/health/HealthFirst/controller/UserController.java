package com.health.HealthFirst.controller;

import com.health.HealthFirst.dto.UserDTO;
import com.health.HealthFirst.dto.UserProfileDTO;
import com.health.HealthFirst.model.User;
import com.health.HealthFirst.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    UserServiceImpl service;

    public UserController(UserServiceImpl service){
        this.service = service;
    }

//    @GetMapping("/users")
//    public ResponseEntity<List<User>> getAllUsers(){
//        return new ResponseEntity<>(service.getAllUsers(), HttpStatus.OK);
//    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable int id){
        Optional<User> user = service.getUserById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(service.getUserById(id), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signUp(@RequestBody UserDTO userDTO){
        return new ResponseEntity<>(service.signUp(userDTO),HttpStatus.CREATED);
    }
    @DeleteMapping("/userDelete/{id}")
    public ResponseEntity<Boolean> deleteAccount(@PathVariable int id){
        return new ResponseEntity<>(service.deleteAccount(id),HttpStatus.OK);
    }

    @GetMapping("/user/me")
    public ResponseEntity<User> getCurrentUser(){
        return service.findByUserName(org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName())
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/user/profile")
    public ResponseEntity<User> updateProfile(@Valid @RequestBody UserProfileDTO profileDTO){
        User updated = service.updateCurrentUserProfile(profileDTO);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

}
