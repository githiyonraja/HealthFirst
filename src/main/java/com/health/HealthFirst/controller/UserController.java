package com.health.HealthFirst.controller;

import com.health.HealthFirst.model.User;
import com.health.HealthFirst.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    UserServiceImpl service;

    public UserController(UserServiceImpl service){
        this.service = service;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(service.getAllUsers(), HttpStatus.OK);
    }

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
    public ResponseEntity<User> signUp(@RequestBody User user){
        return new ResponseEntity<>(service.signUp(user),HttpStatus.CREATED);
    }
    @DeleteMapping("/userDelete/{id}")
    public ResponseEntity<Boolean> deleteAccount(@PathVariable int id){
        return new ResponseEntity<>(service.deleteAccount(id),HttpStatus.OK);
    }

}
