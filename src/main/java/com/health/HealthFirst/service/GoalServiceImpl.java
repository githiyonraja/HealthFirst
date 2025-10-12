package com.health.HealthFirst.service;

import com.health.HealthFirst.model.Goal;
import com.health.HealthFirst.model.User;
import com.health.HealthFirst.repository.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class GoalServiceImpl implements GoalService{
    @Autowired
    private GoalRepository goalRepo;

    @Override
    public Optional<Goal> getPrimGoalByUserId(User userId) {
        return goalRepo.findById(userId);
    }

    @Override
    public Optional<Goal> getGoalWeightByUserId(User userId) {
        return goalRepo.findById(userId);
    }

//    @Override
//    public Goal addPrimGoalByUserId(User userId, Goal primGoal) {
//        return goalRepo.saveAll(userId,primGoal);
//    }
//
//    @Override
//    public Goal addGoalWeightByUserId(User userId, Goal goalWeight) {
//        return goalRepo.saveAll(userId, goalWeight);
//    }
}
