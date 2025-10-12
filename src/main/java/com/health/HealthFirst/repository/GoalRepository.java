package com.health.HealthFirst.repository;

import com.health.HealthFirst.model.Goal;
import com.health.HealthFirst.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalRepository extends JpaRepository<Goal, User> {
    //Goal saveAll(User userId, Goal primGoal);
}
