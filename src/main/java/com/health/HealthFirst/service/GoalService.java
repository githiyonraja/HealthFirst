package com.health.HealthFirst.service;

import com.health.HealthFirst.model.Goal;
import com.health.HealthFirst.model.User;

import java.util.Optional;

public interface GoalService {
    Optional<Goal> getPrimGoalByUserId(User userId);
    Optional<Goal> getGoalWeightByUserId(User userId);
//    Goal addPrimGoalByUserId(User userId, Goal primGoal);
//    Goal addGoalWeightByUserId(User userId, Goal goalWeight);
}
