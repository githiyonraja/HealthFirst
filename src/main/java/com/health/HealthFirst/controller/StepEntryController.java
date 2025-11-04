package com.health.HealthFirst.controller;

import com.health.HealthFirst.dto.StepEntryDTOs;
import com.health.HealthFirst.model.StepEntry;
import com.health.HealthFirst.service.StepEntryService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/steps")
public class StepEntryController {
    private final StepEntryService service;

    public StepEntryController(StepEntryService service) {
        this.service = service;
    }

    @PostMapping
    public StepEntryDTOs.Response create(@Valid @RequestBody StepEntryDTOs.CreateOrUpdateRequest request) {
        StepEntry saved = service.create(request);
        return toResponse(saved);
    }

    @PutMapping("/{id}")
    public StepEntryDTOs.Response update(@PathVariable Long id, @Valid @RequestBody StepEntryDTOs.CreateOrUpdateRequest request) {
        StepEntry saved = service.update(id, request);
        return toResponse(saved);
    }

    @GetMapping
    public StepEntryDTOs.Response getByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        StepEntry entry = service.getByDate(date);
        return entry == null ? null : toResponse(entry);
    }

    @GetMapping("/range")
    public List<StepEntryDTOs.Response> getRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return service.getRange(start, end).stream().map(this::toResponse).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    private StepEntryDTOs.Response toResponse(StepEntry entry) {
        StepEntryDTOs.Response r = new StepEntryDTOs.Response();
        r.id = entry.getId();
        r.entryDate = entry.getEntryDate();
        r.steps = entry.getSteps();
        return r;
    }
}


