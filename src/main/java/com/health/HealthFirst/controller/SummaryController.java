package com.health.HealthFirst.controller;

import com.health.HealthFirst.model.CalorieEntry;
import com.health.HealthFirst.model.StepEntry;
import com.health.HealthFirst.model.WeightEntry;
import com.health.HealthFirst.service.CalorieEntryService;
import com.health.HealthFirst.service.StepEntryService;
import com.health.HealthFirst.service.WeightEntryService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/summary")
public class SummaryController {
    private final StepEntryService stepService;
    private final CalorieEntryService calorieService;
    private final WeightEntryService weightService;

    public SummaryController(StepEntryService stepService, CalorieEntryService calorieService, WeightEntryService weightService) {
        this.stepService = stepService;
        this.calorieService = calorieService;
        this.weightService = weightService;
    }

    @GetMapping("/today")
    public Map<String, Object> today() {
        LocalDate today = LocalDate.now();
        Map<String, Object> map = new HashMap<>();
        StepEntry steps = stepService.getByDate(today);
        CalorieEntry calories = calorieService.getByDate(today);
        WeightEntry weight = weightService.getByDate(today);
        map.put("date", today.toString());
        map.put("steps", steps == null ? null : steps.getSteps());
        map.put("caloriesIn", calories == null ? null : calories.getCaloriesIn());
        map.put("caloriesOut", calories == null ? null : calories.getCaloriesOut());
        map.put("weightKg", weight == null ? null : weight.getWeightKg());
        return map;
    }

    @GetMapping("/range")
    public Map<String, Object> range(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        Map<String, Object> map = new HashMap<>();
        List<StepEntry> steps = stepService.getRange(start, end);
        List<CalorieEntry> calories = calorieService.getRange(start, end);
        List<WeightEntry> weights = weightService.getRange(start, end);
        map.put("start", start.toString());
        map.put("end", end.toString());
        map.put("steps", steps);
        map.put("calories", calories);
        map.put("weights", weights);
        return map;
    }
}


