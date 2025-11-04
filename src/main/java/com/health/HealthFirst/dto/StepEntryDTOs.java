package com.health.HealthFirst.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class StepEntryDTOs {
    public static class CreateOrUpdateRequest {
        @NotNull
        public LocalDate entryDate;

        @NotNull
        @Min(0)
        public Integer steps;
    }

    public static class Response {
        public Long id;
        public LocalDate entryDate;
        public Integer steps;
    }
}


