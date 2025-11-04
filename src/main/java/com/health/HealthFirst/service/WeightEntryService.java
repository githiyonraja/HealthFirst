package com.health.HealthFirst.service;

import com.health.HealthFirst.dto.WeightEntryDTOs;
import com.health.HealthFirst.model.WeightEntry;

import java.time.LocalDate;
import java.util.List;

public interface WeightEntryService {
    WeightEntry upsert(WeightEntryDTOs.CreateOrUpdateRequest request);
    WeightEntry getByDate(LocalDate date);
    List<WeightEntry> getRange(LocalDate start, LocalDate end);
}


