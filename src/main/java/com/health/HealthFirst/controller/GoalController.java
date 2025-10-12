package com.health.HealthFirst.controller;

import com.health.HealthFirst.model.Goal;
import com.health.HealthFirst.service.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/goals")
public class GoalController {
    @Autowired
    private GoalService service;

    @GetMapping("/Goal/{id}")
    public ResponseEntity<Optional<Goal>> getGoalByUserId(Long goalId){
        return new ResponseEntity<>(service.getPrimGoalById(goalId), HttpStatus.OK);
    }
}
