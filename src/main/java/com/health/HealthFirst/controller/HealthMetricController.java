package com.health.HealthFirst.controller;

import com.health.HealthFirst.service.HealthMetricSerciveImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HealthMetricController {

    HealthMetricSerciveImpl healthMetricSercive;

    public HealthMetricController(HealthMetricSerciveImpl healthMetricSercive) {
        this.healthMetricSercive = healthMetricSercive;
    }

    @GetMapping("/metrics/{id}")
    public ResponseEntity<Integer> getHeartRateById(@PathVariable Integer id){
        return new ResponseEntity<>(healthMetricSercive.getHeartRateById(id), HttpStatus.OK);
    }
}
