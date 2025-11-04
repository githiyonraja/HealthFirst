package com.health.HealthFirst.controller;

import com.health.HealthFirst.dto.WeightEntryDTOs;
import com.health.HealthFirst.model.WeightEntry;
import com.health.HealthFirst.service.WeightEntryService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/weight")
public class WeightEntryController {
    private final WeightEntryService service;

    public WeightEntryController(WeightEntryService service) {
        this.service = service;
    }

    @PostMapping
    public WeightEntryDTOs.Response upsert(@Valid @RequestBody WeightEntryDTOs.CreateOrUpdateRequest request) {
        WeightEntry saved = service.upsert(request);
        return toResponse(saved);
    }

    @GetMapping
    public WeightEntryDTOs.Response getByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        WeightEntry entry = service.getByDate(date);
        return entry == null ? null : toResponse(entry);
    }

    @GetMapping("/range")
    public List<WeightEntryDTOs.Response> getRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return service.getRange(start, end).stream().map(this::toResponse).collect(Collectors.toList());
    }

    private WeightEntryDTOs.Response toResponse(WeightEntry entry) {
        WeightEntryDTOs.Response r = new WeightEntryDTOs.Response();
        r.id = entry.getId();
        r.entryDate = entry.getEntryDate();
        r.weightKg = entry.getWeightKg();
        return r;
    }
}


