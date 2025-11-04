package com.health.HealthFirst.service;

import com.health.HealthFirst.dto.CalorieEntryDTOs;
import com.health.HealthFirst.model.CalorieEntry;
import com.health.HealthFirst.model.User;
import com.health.HealthFirst.repository.CalorieEntryRepository;
import com.health.HealthFirst.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CalorieEntryServiceImpl implements CalorieEntryService {
    private final CalorieEntryRepository repository;
    private final UserRepository userRepository;

    public CalorieEntryServiceImpl(CalorieEntryRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public CalorieEntry create(CalorieEntryDTOs.CreateOrUpdateRequest request) {
        User currentUser = getCurrentUser();
        CalorieEntry entry = new CalorieEntry();
        entry.setUser(currentUser);
        entry.setEntryDate(request.entryDate);
        entry.setCaloriesIn(request.caloriesIn);
        entry.setCaloriesOut(request.caloriesOut);
        return repository.save(entry);
    }

    @Override
    public CalorieEntry update(Long id, CalorieEntryDTOs.CreateOrUpdateRequest request) {
        User currentUser = getCurrentUser();
        CalorieEntry entry = repository.findById(id).orElseThrow(() -> new RuntimeException("Calorie entry not found"));
        if (entry.getUser().getUserId() != currentUser.getUserId()) {
            throw new RuntimeException("Forbidden");
        }
        entry.setEntryDate(request.entryDate);
        entry.setCaloriesIn(request.caloriesIn);
        entry.setCaloriesOut(request.caloriesOut);
        return repository.save(entry);
    }

    @Override
    public CalorieEntry getByDate(LocalDate date) {
        User currentUser = getCurrentUser();
        return repository.findByUserAndEntryDate(currentUser, date).orElse(null);
    }

    @Override
    public List<CalorieEntry> getRange(LocalDate start, LocalDate end) {
        User currentUser = getCurrentUser();
        return repository.findAllByUserAndEntryDateBetween(currentUser, start, end);
    }

    @Override
    public void delete(Long id) {
        User currentUser = getCurrentUser();
        CalorieEntry entry = repository.findById(id).orElseThrow(() -> new RuntimeException("Calorie entry not found"));
        if (entry.getUser().getUserId() != currentUser.getUserId()) {
            throw new RuntimeException("Forbidden");
        }
        repository.delete(entry);
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
    }
}


