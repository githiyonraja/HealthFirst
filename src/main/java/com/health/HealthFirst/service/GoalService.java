package com.health.HealthFirst.service;

import com.health.HealthFirst.model.Goal;

import java.util.Optional;

public interface GoalService {
    Optional<Goal> getPrimGoalById(Long goalId);
    Optional<Goal> getGoalWeightById(Long goalId);
//    Goal addPrimGoalByUserId(User userId, Goal primGoal);
//    Goal addGoalWeightByUserId(User userId, Goal goalWeight);
}
