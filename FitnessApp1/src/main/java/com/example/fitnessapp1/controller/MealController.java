package com.example.fitnessapp1.controller;

import com.example.fitnessapp1.resource.request.AddMealRequest;
import com.example.fitnessapp1.resource.response.MealResponse;
import com.example.fitnessapp1.service.MealService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/meal")
@RequiredArgsConstructor
public class MealController {
    private final MealService mealService;

    @PostMapping("/add")
    private ResponseEntity<MealResponse> addMeal(@Valid @RequestBody AddMealRequest addMealRequest) {
        return ResponseEntity.ok(mealService.add(addMealRequest));
    }

    @GetMapping("/{id}")
    private ResponseEntity<MealResponse> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(mealService.getById(id));
    }

    @GetMapping("/name/{name}")
    private ResponseEntity<MealResponse> getByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(mealService.getByName(name));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> delete(@PathVariable("id") Long id) {
        mealService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
