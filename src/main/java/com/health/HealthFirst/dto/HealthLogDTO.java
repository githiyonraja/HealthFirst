package com.health.HealthFirst.dto;

import com.health.HealthFirst.model.User;

import java.time.LocalDate;

public class HealthLogDTO {

    private LocalDate date;
    private int stepsCovered;
    private double caloriesBurned;
    private double sleepHours;
    private User user;

    public HealthLogDTO() {

    }

    public HealthLogDTO(LocalDate date, int stepsCovered, double caloriesBurned, double sleepHours, User user) {
        this.date = date;
        this.stepsCovered = stepsCovered;
        this.caloriesBurned = caloriesBurned;
        this.sleepHours = sleepHours;
        this.user = user;
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
}
