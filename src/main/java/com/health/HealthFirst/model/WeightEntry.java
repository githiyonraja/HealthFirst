package com.health.HealthFirst.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class WeightEntry {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "sequenceGenerator"
    )
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(nullable = false)
    private LocalDate entryDate;

    @Column(nullable = false)
    private Double weightKg;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public Double getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(Double weightKg) {
        this.weightKg = weightKg;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}


