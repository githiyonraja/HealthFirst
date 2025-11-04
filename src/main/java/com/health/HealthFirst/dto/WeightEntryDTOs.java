package com.health.HealthFirst.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

public class WeightEntryDTOs {
    public static class CreateOrUpdateRequest {
        @NotNull
        public LocalDate entryDate;

        @NotNull
        @Positive
        public Double weightKg;
    }

    public static class Response {
        public Long id;
        public LocalDate entryDate;
        public Double weightKg;
    }
}


