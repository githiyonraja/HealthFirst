package com.health.HealthFirst.controller;

import com.health.HealthFirst.dto.CalorieEntryDTOs;
import com.health.HealthFirst.model.CalorieEntry;
import com.health.HealthFirst.service.CalorieEntryService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/calories")
public class CalorieEntryController {
    private final CalorieEntryService service;

    public CalorieEntryController(CalorieEntryService service) {
        this.service = service;
    }

    @PostMapping
    public CalorieEntryDTOs.Response create(@Valid @RequestBody CalorieEntryDTOs.CreateOrUpdateRequest request) {
        CalorieEntry saved = service.create(request);
        return toResponse(saved);
    }

    @PutMapping("/{id}")
    public CalorieEntryDTOs.Response update(@PathVariable Long id, @Valid @RequestBody CalorieEntryDTOs.CreateOrUpdateRequest request) {
        CalorieEntry saved = service.update(id, request);
        return toResponse(saved);
    }

    @GetMapping
    public CalorieEntryDTOs.Response getByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        CalorieEntry entry = service.getByDate(date);
        return entry == null ? null : toResponse(entry);
    }

    @GetMapping("/range")
    public List<CalorieEntryDTOs.Response> getRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return service.getRange(start, end).stream().map(this::toResponse).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    private CalorieEntryDTOs.Response toResponse(CalorieEntry entry) {
        CalorieEntryDTOs.Response r = new CalorieEntryDTOs.Response();
        r.id = entry.getId();
        r.entryDate = entry.getEntryDate();
        r.caloriesIn = entry.getCaloriesIn();
        r.caloriesOut = entry.getCaloriesOut();
        return r;
    }
}


