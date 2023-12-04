package com.example.fitnessapp1.controller;

import com.example.fitnessapp1.resource.request.MealRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/meal")
@RequiredArgsConstructor
public class MealController {
    @PostMapping("/add")
    private void addMeal(@Valid @RequestBody MealRequest request) {
        System.out.println(request.toString());
    }
}
