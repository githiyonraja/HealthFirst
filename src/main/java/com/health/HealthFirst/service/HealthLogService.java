package com.health.HealthFirst.service;

import com.health.HealthFirst.model.HealthLog;

import java.util.Optional;

public interface HealthLogService {
    Optional<HealthLog> getHealthLogsByUser(Long userId);
    HealthLog addHealthLog(Long userId,HealthLog healthLog);
}
