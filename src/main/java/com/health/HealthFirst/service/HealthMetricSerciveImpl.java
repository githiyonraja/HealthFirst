package com.health.HealthFirst.service;

import com.health.HealthFirst.model.HealthMetrics;
import com.health.HealthFirst.repository.HealthMetricRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HealthMetricSerciveImpl implements HealthMetricService{

    HealthMetricRepository healthMetricRepository;

    public HealthMetricSerciveImpl(HealthMetricRepository healthMetricRepository) {
        this.healthMetricRepository = healthMetricRepository;
    }

    @Override
    public HealthMetrics saveMetrics(HealthMetrics healthMetrics) {
        return healthMetricRepository.save(healthMetrics);
    }

    @Override
    public Optional<HealthMetrics> getMetricsById(Integer id) {
        return healthMetricRepository.findById(id);
    }

    @Override
    public void deleteMetrics(Integer id) {
        healthMetricRepository.deleteById(id);
    }

    @Override
    public Integer getHeartRateById(Integer id) {
        return healthMetricRepository.findById(id)
                .map(HealthMetrics::getHeartRate)
                .orElse(null);
    }

    @Override
    public void setHeartRateById(Integer id, Integer heartRate) {
        healthMetricRepository.findById(id).ifPresent(metrics -> {
            metrics.setHeartRate(heartRate);
            healthMetricRepository.save(metrics);
        });
    }

    @Override
    public Integer getOxygenLevelById(Integer id) {
        return healthMetricRepository.findById(id)
                .map(HealthMetrics::getsPO2)
                .orElse(null);
    }

    @Override
    public void setOxygenLevelById(Integer id, Integer oxygenLevel) {
        healthMetricRepository.findById(id).ifPresent(metrics -> {
            metrics.setsPO2(oxygenLevel);
            healthMetricRepository.save(metrics);
        });
    }

    @Override
    public Integer getStressLevelById(Integer id) {
        return healthMetricRepository.findById(id)
                .map(HealthMetrics::getStress)
                .orElse(null);
    }

    @Override
    public void setStressLevelById(Integer id, Integer stressLevel) {
        healthMetricRepository.findById(id).ifPresent(metrics -> {
            metrics.setStress(stressLevel);
            healthMetricRepository.save(metrics);
        });
    }
}
