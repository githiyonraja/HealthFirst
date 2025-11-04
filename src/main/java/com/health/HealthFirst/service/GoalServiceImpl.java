package com.health.HealthFirst.service;

import com.health.HealthFirst.model.Goal;
import com.health.HealthFirst.repository.GoalRepository;
import com.health.HealthFirst.dto.GoalDTOs;
import com.health.HealthFirst.model.User;
import com.health.HealthFirst.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;
import java.util.List;
@Service
public class GoalServiceImpl implements GoalService{
    @Autowired
    private GoalRepository goalRepo;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<Goal> getPrimGoalById(Long goalId) {
        return goalRepo.findById(goalId);
    }

    @Override
    public Optional<Goal> getGoalWeightById(Long goalId) {
        return goalRepo.findById(goalId);
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

    @Override
    public Goal createForCurrentUser(GoalDTOs.CreateRequest request) {
        User currentUser = getCurrentUser();
        Goal g = new Goal();
        g.setPrimaryGoal(request.primaryGoal);
        g.setGoalWeight(request.goalWeight);
        g.setUser(currentUser);
        return goalRepo.save(g);
    }

    @Override
    public List<Goal> listForCurrentUser() {
        User currentUser = getCurrentUser();
        return goalRepo.findAllByUser(currentUser);
    }

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
