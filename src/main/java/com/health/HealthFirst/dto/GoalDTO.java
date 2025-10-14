package com.health.HealthFirst.dto;

import jakarta.persistence.Column;

public class GoalDTO {

    private String primaryGoal;
    private Double goalWeight;

    public GoalDTO() {
    }

    public GoalDTO(String primaryGoal, Double goalWeight) {
        this.primaryGoal = primaryGoal;
        this.goalWeight = goalWeight;
    }

    public String getPrimaryGoal() {
        return primaryGoal;
    }

    public void setPrimaryGoal(String primaryGoal) {
        this.primaryGoal = primaryGoal;
    }

    public Double getGoalWeight() {
        return goalWeight;
    }

    public void setGoalWeight(Double goalWeight) {
        this.goalWeight = goalWeight;
    }
}
