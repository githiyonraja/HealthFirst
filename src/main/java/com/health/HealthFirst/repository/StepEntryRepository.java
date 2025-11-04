package com.health.HealthFirst.repository;

import com.health.HealthFirst.model.StepEntry;
import com.health.HealthFirst.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface StepEntryRepository extends JpaRepository<StepEntry, Long> {
    Optional<StepEntry> findByUserAndEntryDate(User user, LocalDate entryDate);
    List<StepEntry> findAllByUserAndEntryDateBetween(User user, LocalDate start, LocalDate end);
}


