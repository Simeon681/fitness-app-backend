package com.example.fitnessapp1.service;

import com.example.fitnessapp1.resource.request.AddMealRequest;
import com.example.fitnessapp1.resource.response.MealResponse;

public interface MealService {
    MealResponse add(AddMealRequest addMealRequest);
    MealResponse getById(Long id);
    MealResponse getByName(String name);
    void delete(Long id);
}
