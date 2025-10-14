package com.health.HealthFirst.controller;

import com.health.HealthFirst.dto.HealthLogDTO;
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

    private HealthLogServiceImpl service;

    @Autowired
    public HealthLogController(HealthLogServiceImpl service){
        this.service = service;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Optional<HealthLog>> findHealthLogByUser(@PathVariable Long userId){
        return new ResponseEntity<>(service.getHealthLogsByUser(userId), HttpStatus.OK);
    }
    @PostMapping("/addLogsByUser")
    public ResponseEntity<HealthLogDTO> addHealthLogByUser(Long userId, HealthLogDTO healthLogDTO){
        return new ResponseEntity<>(service.addHealthLog(userId,healthLogDTO),HttpStatus.CREATED);
    }

}
