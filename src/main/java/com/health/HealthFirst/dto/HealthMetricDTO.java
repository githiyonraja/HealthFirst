package com.health.HealthFirst.dto;

public class HealthMetricDTO {

    private Integer heartRate;
    private Integer sPO2;
    private Integer stress;

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

}
