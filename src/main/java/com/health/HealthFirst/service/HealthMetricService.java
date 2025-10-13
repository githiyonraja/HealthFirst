package com.health.HealthFirst.service;

import com.health.HealthFirst.dto.HealthMetricDTO;

import java.util.Optional;

public interface HealthMetricService {
    HealthMetricDTO saveMetrics(HealthMetricDTO healthMetricDTO);
    Optional<HealthMetricDTO> getMetricsById(Integer id);
    void deleteMetrics(Integer id);

    Integer getHeartRateById(Integer id);
    void setHeartRateById(Integer id, Integer heartRate);

    Integer getOxygenLevelById(Integer id);
    void setOxygenLevelById(Integer id, Integer oxygenLevel);

    Integer getStressLevelById(Integer id);
    void setStressLevelById(Integer id, Integer stressLevel);
}
