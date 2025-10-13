package com.health.HealthFirst.service;

import com.health.HealthFirst.dto.HealthMetricDTO;
import com.health.HealthFirst.model.HealthMetric;
import com.health.HealthFirst.repository.HealthMetricRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HealthMetricServiceImpl implements HealthMetricService{

    private final HealthMetricRepository healthMetricRepository;
    private final ModelMapper modelMapper;

    public HealthMetricServiceImpl(HealthMetricRepository healthMetricRepository, ModelMapper modelMapper) {
        this.healthMetricRepository = healthMetricRepository;
        this.modelMapper = modelMapper;
    }

    private HealthMetricDTO convertToDTO(HealthMetric entity) {
        return modelMapper.map(entity, HealthMetricDTO.class);
    }

    private HealthMetric convertToEntity(HealthMetricDTO dto) {
        return modelMapper.map(dto, HealthMetric.class);
    }

    @Override
    public HealthMetricDTO saveMetrics(HealthMetricDTO healthMetricDTO) {
        HealthMetric entity = convertToEntity(healthMetricDTO);
        HealthMetric saved = healthMetricRepository.save(entity);
        return convertToDTO(saved);
    }

    @Override
    public Optional<HealthMetricDTO> getMetricsById(Integer id) {
        return healthMetricRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public void deleteMetrics(Integer id) {
        healthMetricRepository.deleteById(id);
    }

    @Override
    public Integer getHeartRateById(Integer metricId) {
        return healthMetricRepository.findById(metricId)
                .map(HealthMetric::getHeartRate)
                .orElse(null);
    }

    @Override
    public void setHeartRateById(Integer metricId, Integer heartRate) {
        healthMetricRepository.findById(metricId).ifPresent(metrics -> {
            metrics.setHeartRate(heartRate);
            healthMetricRepository.save(metrics);
        });
    }

    @Override
    public Integer getOxygenLevelById(Integer metricId) {
        return healthMetricRepository.findById(metricId)
                .map(HealthMetric::getsPO2)
                .orElse(null);
    }

    @Override
    public void setOxygenLevelById(Integer metricId, Integer oxygenLevel) {
        healthMetricRepository.findById(metricId).ifPresent(metrics -> {
            metrics.setsPO2(oxygenLevel);
            healthMetricRepository.save(metrics);
        });
    }

    @Override
    public Integer getStressLevelById(Integer metricId) {
        return healthMetricRepository.findById(metricId)
                .map(HealthMetric::getStress)
                .orElse(null);
    }

    public void setStressLevelById(Integer metricId, Integer stressLevel) {
        healthMetricRepository.findById(metricId).ifPresent(metrics -> {
            metrics.setStress(stressLevel);
            healthMetricRepository.save(metrics);
        });
    }
}
