package com.example.fitnessapp1.service;

import com.example.fitnessapp1.resource.request.AddMealStatRequest;
import com.example.fitnessapp1.resource.response.MealStatResponse;

public interface MealStatService {
    MealStatResponse create(AddMealStatRequest addMealStatRequest, Long id, Long mealId);
    void delete(Long id);
}
