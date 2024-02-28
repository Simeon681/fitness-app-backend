package com.example.fitnessapp1.controller;

import com.example.fitnessapp1.resource.request.AddMealStatRequest;
import com.example.fitnessapp1.resource.response.MealStatResponse;
import com.example.fitnessapp1.service.MealStatService;
import com.example.fitnessapp1.shared.MealType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/meal-stat")
@RequiredArgsConstructor
public class MealStatController {
    private final MealStatService mealStatService;

    @PostMapping("/create")
    private ResponseEntity<MealStatResponse> createMealStat(
            @Valid @RequestBody AddMealStatRequest mealStatRequest,
            @RequestParam("mealId") Long mealId
    ) {

        System.out.println("mealStatRequest: " + mealStatRequest);
        return ResponseEntity.ok(mealStatService.create(mealStatRequest, mealId));
    }

    @GetMapping("/search-by-date")
    private ResponseEntity<?> searchByDate(@RequestParam("date") LocalDate date) {
        return ResponseEntity.ok(mealStatService.searchMealStatByDate(date));
    }

    @GetMapping
    private ResponseEntity<?> searchByUserIdAndDateAndTypeBreakfast(@RequestParam("type") MealType type) {
        return ResponseEntity.ok(mealStatService.searchMealStatByUserIdAndDateAndType(type));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> delete(@PathVariable("id") Long id) {
        mealStatService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
