package com.health.HealthFirst.service;

import com.health.HealthFirst.dto.HealthLogDTO;
import com.health.HealthFirst.model.HealthLog;

import java.util.Optional;

public interface HealthLogService {
    Optional<HealthLog> getHealthLogsByUser(Long userId);
    HealthLogDTO addHealthLog(Long userId, HealthLogDTO healthLogDTO);
}
