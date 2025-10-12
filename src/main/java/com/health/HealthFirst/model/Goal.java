package com.health.HealthFirst.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long goalId;

    @Column(name = "primary_goal")
    private String primaryGoal;

    @Column(name = "goal_weight")
    private Double goalWeight;

    public Goal(String primaryGoal, Double goalWeight) {
        this.primaryGoal = primaryGoal;
        this.goalWeight = goalWeight;
    }

    public long getGoalId() {
        return goalId;
    }

    public void setGoalId(long goalId) {
        this.goalId = goalId;
    }

    public String getPrimaryGoal() {
        return primaryGoal;
    }

    public void setPrimaryGoal(String primaryGoal) {
        this.primaryGoal = primaryGoal;
    }

    public double getGoalWeight() {
        return goalWeight;
    }

    public void setGoalWeight(double goalWeight) {
        this.goalWeight = goalWeight;
    }
}
