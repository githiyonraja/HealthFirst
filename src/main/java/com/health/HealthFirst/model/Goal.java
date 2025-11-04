package com.health.HealthFirst.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long goalId;

    @Column(name = "primary_goal")
    private String primaryGoal;

    @Column(name = "goal_weight")
    private Double goalWeight;

    @ManyToOne
    @JoinColumn(
            name = "user_Id",
            nullable = false
    )
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Goal{" +
                "goalId=" + goalId +
                ", primaryGoal='" + primaryGoal + '\'' +
                ", goalWeight=" + goalWeight +
                ", user=" + user +
                '}';
    }
}
