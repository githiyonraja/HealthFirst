package com.health.HealthFirst.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class CalorieEntryDTOs {
    public static class CreateOrUpdateRequest {
        @NotNull
        public LocalDate entryDate;

        @NotNull
        @Min(0)
        public Integer caloriesIn;

        @NotNull
        @Min(0)
        public Integer caloriesOut;
    }

    public static class Response {
        public Long id;
        public LocalDate entryDate;
        public Integer caloriesIn;
        public Integer caloriesOut;
    }
}


