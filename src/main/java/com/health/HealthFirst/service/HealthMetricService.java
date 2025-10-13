package com.health.HealthFirst.service;

import com.health.HealthFirst.model.HealthMetrics;

import java.util.Optional;

public interface HealthMetricService {
    Optional<HealthMetrics> getHeartRateById(Integer id);
    Optional<HealthMetrics> getOxygenLevelById(Integer id);
    Integer getStressLevelById(Integer id);
}
