package com.health.HealthFirst.repository;

import com.health.HealthFirst.model.HealthMetrics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthMetricRepository extends JpaRepository<HealthMetrics, Integer> {

}
