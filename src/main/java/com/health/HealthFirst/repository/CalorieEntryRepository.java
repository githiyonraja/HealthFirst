package com.health.HealthFirst.repository;

import com.health.HealthFirst.model.CalorieEntry;
import com.health.HealthFirst.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CalorieEntryRepository extends JpaRepository<CalorieEntry, Long> {
    Optional<CalorieEntry> findByUserAndEntryDate(User user, LocalDate entryDate);
    List<CalorieEntry> findAllByUserAndEntryDateBetween(User user, LocalDate start, LocalDate end);
}


