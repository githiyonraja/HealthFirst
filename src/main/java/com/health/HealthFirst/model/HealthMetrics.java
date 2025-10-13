package com.health.HealthFirst.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class HealthMetrics {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequenceGenerator"
    )
    @SequenceGenerator(name = "sequenceGenrator")
    private Integer metricId;

    @Column(name = "heart_rate")
    private Integer heartRate;

    @Column(name = "oxygen_level")
    private Integer sPO2;

    @Column(name = "stress_level")
    private Integer stress;

    @ManyToOne
    @JoinColumn(name = "user_Id",
            nullable = false
    )
    private User user;

    public Integer getMetricId() {
        return metricId;
    }

    public void setMetricId(Integer metricId) {
        this.metricId = metricId;
    }

    public Integer getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Integer heartRate) {
        this.heartRate = heartRate;
    }

    public Integer getsPO2() {
        return sPO2;
    }

    public void setsPO2(Integer sPO2) {
        this.sPO2 = sPO2;
    }

    public Integer getStress() {
        return stress;
    }

    public void setStress(Integer stress) {
        this.stress = stress;
    }

    @Override
    public String toString() {
        return "HealthMetrics{" +
                "metricId=" + metricId +
                ", heartRate=" + heartRate +
                ", sPO2=" + sPO2 +
                ", stress=" + stress +
                ", user=" + user +
                '}';
    }
}
