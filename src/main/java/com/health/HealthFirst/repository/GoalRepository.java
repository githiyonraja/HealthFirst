package com.health.HealthFirst.repository;

import com.health.HealthFirst.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import com.health.HealthFirst.model.User;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {
    List<Goal> findAllByUser(User user);
}
