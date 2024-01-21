package com.example.fitnessapp1.controller;

import com.example.fitnessapp1.resource.request.AddMealStatRequest;
import com.example.fitnessapp1.resource.response.MealStatResponse;
import com.example.fitnessapp1.service.MealStatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// TODO: Think about different name for this instead of "MealStat"

@RestController
@RequestMapping("/api/v1/meal-stat")
@RequiredArgsConstructor
public class MealStatController {
    private final MealStatService mealStatService;

    @PostMapping("/create/{id}")
    private ResponseEntity<MealStatResponse> createMealStat(
            @Valid @RequestBody AddMealStatRequest mealStatRequest,
            @PathVariable("id") Long id,
            @RequestParam("mealId") Long mealId
    ) {
        return ResponseEntity.ok(mealStatService.create(mealStatRequest, id, mealId));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> delete(@PathVariable("id") Long id) {
        mealStatService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
