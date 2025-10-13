package com.health.HealthFirst.controller;

import com.health.HealthFirst.dto.HealthMetricDTO;
import com.health.HealthFirst.model.HealthMetric;
import com.health.HealthFirst.service.HealthMetricServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/metrics")
public class HealthMetricController {

    private final HealthMetricServiceImpl healthMetricService;

    @Autowired
    public HealthMetricController(HealthMetricServiceImpl healthMetricService) {
        this.healthMetricService = healthMetricService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<HealthMetricDTO> getMetricsById(@PathVariable Integer id){
        Optional<HealthMetricDTO> metricsById = healthMetricService.getMetricsById(id);
        return metricsById.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<HealthMetricDTO> saveMetrics(@RequestBody HealthMetricDTO healthMetricDTO){
        HealthMetricDTO savedMetrics = healthMetricService.saveMetrics(healthMetricDTO);
        return ResponseEntity.ok(savedMetrics);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMetrics(@PathVariable Integer id) {
        healthMetricService.deleteMetrics(id);
        return ResponseEntity.ok("Health metric deleted successfully.");
    }

    // Get heart rate
    @GetMapping("/{id}/heartRate")
    public ResponseEntity<Integer> getHeartRate(@PathVariable Integer id) {
        Integer heartRate = healthMetricService.getHeartRateById(id);
        return (heartRate != null) ? ResponseEntity.ok(heartRate)
                : ResponseEntity.notFound().build();
    }

    // Update heart rate
    @PutMapping("/{id}/heartRate")
    public ResponseEntity<String> setHeartRate(@PathVariable Integer id,
                                               @RequestParam Integer heartRate) {
        healthMetricService.setHeartRateById(id, heartRate);
        return ResponseEntity.ok("Heart rate updated successfully.");
    }

    // Get oxygen level
    @GetMapping("/{id}/oxygenLevel")
    public ResponseEntity<Integer> getOxygenLevel(@PathVariable Integer id) {
        Integer oxygenLevel = healthMetricService.getOxygenLevelById(id);
        return (oxygenLevel != null) ? ResponseEntity.ok(oxygenLevel)
                : ResponseEntity.notFound().build();
    }

    // Update oxygen level
    @PutMapping("/{id}/oxygenLevel")
    public ResponseEntity<String> setOxygenLevel(@PathVariable Integer id,
                                                 @RequestParam Integer oxygenLevel) {
        healthMetricService.setOxygenLevelById(id, oxygenLevel);
        return ResponseEntity.ok("Oxygen level updated successfully.");
    }

    // Get stress level
    @GetMapping("/{id}/stressLevel")
    public ResponseEntity<Integer> getStressLevel(@PathVariable Integer id) {
        Integer stressLevel = healthMetricService.getStressLevelById(id);
        return (stressLevel != null) ? ResponseEntity.ok(stressLevel)
                : ResponseEntity.notFound().build();
    }

    // Update stress level
    @PutMapping("/{id}/stressLevel")
    public ResponseEntity<String> setStressLevel(@PathVariable Integer id,
                                                 @RequestParam Integer stressLevel) {
        healthMetricService.setStressLevelById(id, stressLevel);
        return ResponseEntity.ok("Stress level updated successfully.");
    }
}
