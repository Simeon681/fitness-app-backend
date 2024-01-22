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

    @PostMapping("/create")
    private ResponseEntity<MealResponse> createMeal(@Valid @RequestBody AddMealRequest addMealRequest) {
        return ResponseEntity.ok(mealService.create(addMealRequest));
    }

    @GetMapping("/search")
    private ResponseEntity<?> searchMealByName(@RequestParam("mealName") String mealName) {
        return ResponseEntity.ok(mealService.searchMealByName(mealName));
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
