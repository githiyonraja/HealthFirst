package com.health.HealthFirst.service;

import com.health.HealthFirst.dto.StepEntryDTOs;
import com.health.HealthFirst.model.StepEntry;

import java.time.LocalDate;
import java.util.List;

public interface StepEntryService {
    StepEntry create(StepEntryDTOs.CreateOrUpdateRequest request);
    StepEntry update(Long id, StepEntryDTOs.CreateOrUpdateRequest request);
    StepEntry getByDate(LocalDate date);
    List<StepEntry> getRange(LocalDate start, LocalDate end);
    void delete(Long id);
}


