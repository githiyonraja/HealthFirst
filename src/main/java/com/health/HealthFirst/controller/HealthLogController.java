package com.health.HealthFirst.controller;

import com.health.HealthFirst.model.HealthLog;
import com.health.HealthFirst.service.HealthLogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/healthLog")
public class HealthLogController {

    HealthLogServiceImpl service;

    public HealthLogController(HealthLogServiceImpl service){
        this.service = service;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Optional<HealthLog>> findHealthLogByUser(@PathVariable Long userId){
        return new ResponseEntity<>(service.getHealthLogsByUser(userId), HttpStatus.OK);
    }
    @PostMapping("/addLogsByUser")
    public ResponseEntity<HealthLog> addHealthLogByUser(Long userId, HealthLog healthLog){
        return new ResponseEntity<>(service.addHealthLog(userId,healthLog),HttpStatus.CREATED);
    }

}
