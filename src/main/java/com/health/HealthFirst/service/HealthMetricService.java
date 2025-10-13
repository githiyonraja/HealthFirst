package com.health.HealthFirst.service;

import com.health.HealthFirst.model.HealthMetrics;

import java.util.List;
import java.util.Optional;

public interface HealthMetricService {
    HealthMetrics saveMetrics(HealthMetrics healthMetrics);
    Optional<HealthMetrics> getMetricsById(Integer id);
    void deleteMetrics(Integer id);

    Integer getHeartRateById(Integer id);
    void setHeartRateById(Integer id, Integer heartRate);

    Integer getOxygenLevelById(Integer id);
    void setOxygenLevelById(Integer id, Integer oxygenLevel);

    Integer getStressLevelById(Integer id);
    void setStressLevelById(Integer id, Integer stressLevel);
}
