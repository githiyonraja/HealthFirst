package com.health.HealthFirst.repository;

import com.health.HealthFirst.model.WeightEntry;
import com.health.HealthFirst.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface WeightEntryRepository extends JpaRepository<WeightEntry, Long> {
    Optional<WeightEntry> findByUserAndEntryDate(User user, LocalDate entryDate);
    List<WeightEntry> findAllByUserAndEntryDateBetween(User user, LocalDate start, LocalDate end);
}


