package com.health.HealthFirst.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class GoalDTOs {
    public static class CreateRequest {
        @NotBlank
        public String primaryGoal;

        @NotNull
        @Positive
        public Double goalWeight;
    }

    public static class Response {
        public Long goalId;
        public String primaryGoal;
        public Double goalWeight;
    }
}


