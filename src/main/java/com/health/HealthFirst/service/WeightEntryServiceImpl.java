package com.health.HealthFirst.service;

import com.health.HealthFirst.dto.WeightEntryDTOs;
import com.health.HealthFirst.model.User;
import com.health.HealthFirst.model.WeightEntry;
import com.health.HealthFirst.repository.UserRepository;
import com.health.HealthFirst.repository.WeightEntryRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class WeightEntryServiceImpl implements WeightEntryService {
    private final WeightEntryRepository repository;
    private final UserRepository userRepository;

    public WeightEntryServiceImpl(WeightEntryRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public WeightEntry upsert(WeightEntryDTOs.CreateOrUpdateRequest request) {
        User currentUser = getCurrentUser();
        WeightEntry entry = repository.findByUserAndEntryDate(currentUser, request.entryDate)
                .orElseGet(() -> {
                    WeightEntry e = new WeightEntry();
                    e.setUser(currentUser);
                    e.setEntryDate(request.entryDate);
                    return e;
                });
        entry.setWeightKg(request.weightKg);
        return repository.save(entry);
    }

    @Override
    public WeightEntry getByDate(LocalDate date) {
        User currentUser = getCurrentUser();
        return repository.findByUserAndEntryDate(currentUser, date).orElse(null);
    }

    @Override
    public List<WeightEntry> getRange(LocalDate start, LocalDate end) {
        User currentUser = getCurrentUser();
        return repository.findAllByUserAndEntryDateBetween(currentUser, start, end);
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
    }
}


