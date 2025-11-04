package com.health.HealthFirst.service;

import com.health.HealthFirst.dto.CalorieEntryDTOs;
import com.health.HealthFirst.model.CalorieEntry;

import java.time.LocalDate;
import java.util.List;

public interface CalorieEntryService {
    CalorieEntry create(CalorieEntryDTOs.CreateOrUpdateRequest request);
    CalorieEntry update(Long id, CalorieEntryDTOs.CreateOrUpdateRequest request);
    CalorieEntry getByDate(LocalDate date);
    List<CalorieEntry> getRange(LocalDate start, LocalDate end);
    void delete(Long id);
}


