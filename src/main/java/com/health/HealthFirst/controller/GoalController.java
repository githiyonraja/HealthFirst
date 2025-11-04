package com.health.HealthFirst.controller;

import com.health.HealthFirst.model.Goal;
import com.health.HealthFirst.service.GoalService;
import com.health.HealthFirst.dto.GoalDTOs;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/goals")
public class GoalController {
    GoalService service;

    public GoalController(GoalService service) {
        this.service = service;
    }

    @GetMapping("/Goal/{id}")
    public ResponseEntity<Optional<Goal>> getGoalByUserId(Long goalId){
        return new ResponseEntity<>(service.getPrimGoalById(goalId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GoalDTOs.Response> addGoal(@Valid @RequestBody GoalDTOs.CreateRequest request) {
        Goal saved = service.createForCurrentUser(request);
        GoalDTOs.Response r = new GoalDTOs.Response();
        r.goalId = saved.getGoalId();
        r.primaryGoal = saved.getPrimaryGoal();
        r.goalWeight = saved.getGoalWeight();
        return ResponseEntity.status(HttpStatus.CREATED).body(r);
    }

    @GetMapping("/mine")
    public ResponseEntity<List<GoalDTOs.Response>> myGoals() {
        List<Goal> goals = service.listForCurrentUser();
        List<GoalDTOs.Response> out = goals.stream().map(g -> {
            GoalDTOs.Response r = new GoalDTOs.Response();
            r.goalId = g.getGoalId();
            r.primaryGoal = g.getPrimaryGoal();
            r.goalWeight = g.getGoalWeight();
            return r;
        }).toList();
        return ResponseEntity.ok(out);
    }
}
