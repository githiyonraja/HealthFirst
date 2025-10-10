package com.health.HealthFirst.service;

import com.health.HealthFirst.model.HealthLog;
import com.health.HealthFirst.repository.HealthLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HealthLogServiceImpl implements HealthLogService{
    @Autowired
    private HealthLogRepository healthLogRepo;

    @Override
    public Optional<HealthLog> getHealthLogsByUser(Long userId) {
        return healthLogRepo.findById(userId);
    }

    @Override
    public HealthLog addHealthLog(Long userId, HealthLog healthLog) {
        return healthLogRepo.save(healthLog);
    }
}
