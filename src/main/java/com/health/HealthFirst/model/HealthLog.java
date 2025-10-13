package com.health.HealthFirst.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HealthLog {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "sequenceGenerator"
    )
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "Id")
    private long id;

    @Column(nullable = false)
    private LocalDate date;
    private int stepsCovered;
    private double caloriesBurned;
    private double sleepHours;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getStepsCovered() {
        return stepsCovered;
    }

    public void setStepsCovered(int stepsCovered) {
        this.stepsCovered = stepsCovered;
    }

    public double getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(double caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    public double getSleepHours() {
        return sleepHours;
    }

    public void setSleepHours(double sleepHours) {
        this.sleepHours = sleepHours;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "HealthLog{" +
                "id=" + id +
                ", date=" + date +
                ", stepsCovered=" + stepsCovered +
                ", caloriesBurned=" + caloriesBurned +
                ", sleepHours=" + sleepHours +
                ", user=" + user +
                '}';
    }
}
