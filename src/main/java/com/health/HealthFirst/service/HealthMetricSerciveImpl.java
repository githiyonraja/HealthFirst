package com.health.HealthFirst.service;

import com.health.HealthFirst.model.HealthMetrics;
import com.health.HealthFirst.repository.HealthMetricRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HealthMetricSerciveImpl implements HealthMetricService{

    HealthMetricRepository healthMetricRepository;

    public HealthMetricSerciveImpl(HealthMetricRepository healthMetricRepository) {
        this.healthMetricRepository = healthMetricRepository;
    }

    @Override
    public Optional<HealthMetrics> getHeartRateById(Integer id) {
        return healthMetricRepository.findById(id).map(user ->{
                user.getHeartRate().notify();
            return user;
        });
    }

    @Override
    public Optional<HealthMetrics> getOxygenLevelById(Integer id) {
        return healthMetricRepository.findById(id);
    }

    @Override
    public Integer getStressLevelById(Integer id) {
        return 0;
    }
}
