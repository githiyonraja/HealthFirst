package com.health.HealthFirst.service;

import com.health.HealthFirst.dto.StepEntryDTOs;
import com.health.HealthFirst.model.StepEntry;
import com.health.HealthFirst.model.User;
import com.health.HealthFirst.repository.StepEntryRepository;
import com.health.HealthFirst.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StepEntryServiceImpl implements StepEntryService {
    private final StepEntryRepository repository;
    private final UserRepository userRepository;

    public StepEntryServiceImpl(StepEntryRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public StepEntry create(StepEntryDTOs.CreateOrUpdateRequest request) {
        User currentUser = getCurrentUser();
        StepEntry entry = new StepEntry();
        entry.setUser(currentUser);
        entry.setEntryDate(request.entryDate);
        entry.setSteps(request.steps);
        return repository.save(entry);
    }

    @Override
    public StepEntry update(Long id, StepEntryDTOs.CreateOrUpdateRequest request) {
        User currentUser = getCurrentUser();
        StepEntry entry = repository.findById(id).orElseThrow(() -> new RuntimeException("Step entry not found"));
        if (entry.getUser().getUserId() != currentUser.getUserId()) {
            throw new RuntimeException("Forbidden");
        }
        entry.setEntryDate(request.entryDate);
        entry.setSteps(request.steps);
        return repository.save(entry);
    }

    @Override
    public StepEntry getByDate(LocalDate date) {
        User currentUser = getCurrentUser();
        return repository.findByUserAndEntryDate(currentUser, date).orElse(null);
    }

    @Override
    public List<StepEntry> getRange(LocalDate start, LocalDate end) {
        User currentUser = getCurrentUser();
        return repository.findAllByUserAndEntryDateBetween(currentUser, start, end);
    }

    @Override
    public void delete(Long id) {
        User currentUser = getCurrentUser();
        StepEntry entry = repository.findById(id).orElseThrow(() -> new RuntimeException("Step entry not found"));
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


