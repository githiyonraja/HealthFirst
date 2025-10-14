package com.health.HealthFirst.service;

import com.health.HealthFirst.dto.HealthLogDTO;
import com.health.HealthFirst.model.HealthLog;
import com.health.HealthFirst.repository.HealthLogRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HealthLogServiceImpl implements HealthLogService{
    @Autowired
    private final HealthLogRepository healthLogRepo;
    private final ModelMapper modelMapper;

    public HealthLogServiceImpl(HealthLogRepository healthLogRepo, ModelMapper modelMapper) {
        this.healthLogRepo = healthLogRepo;
        this.modelMapper = modelMapper;
    }

    private HealthLog toEntity(HealthLogDTO healthLogDTO){
        return modelMapper.map(healthLogDTO, HealthLog.class);
    }

    private HealthLogDTO toDTO(HealthLog healthLog){
        return modelMapper.map(healthLog, HealthLogDTO.class);
    }

    @Override
    public Optional<HealthLog> getHealthLogsByUser(Long userId) {
        return healthLogRepo.findById(userId);
    }

    @Override
    public HealthLogDTO addHealthLog(Long userId, HealthLogDTO healthLogDTO) {
        HealthLog healthLog = toEntity(healthLogDTO);
        HealthLog savedHealthLog = healthLogRepo.save(healthLog);
        return toDTO(savedHealthLog);
    }
}
