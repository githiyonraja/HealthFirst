package com.health.HealthFirst.service;

import com.health.HealthFirst.model.Goal;
import com.health.HealthFirst.dto.GoalDTOs;

import java.util.List;
import java.util.Optional;

public interface GoalService {
    Optional<Goal> getPrimGoalById(Long goalId);
    Optional<Goal> getGoalWeightById(Long goalId);
    Goal createForCurrentUser(GoalDTOs.CreateRequest request);
    List<Goal> listForCurrentUser();
//    Goal addPrimGoalByUserId(User userId, Goal primGoal);
//    Goal addGoalWeightByUserId(User userId, Goal goalWeight);
}
