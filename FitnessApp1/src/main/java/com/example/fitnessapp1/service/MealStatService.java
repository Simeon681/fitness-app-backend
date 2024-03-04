package com.example.fitnessapp1.service;

import com.example.fitnessapp1.resource.request.AddMealStatRequest;
import com.example.fitnessapp1.resource.response.MealStatResponse;
import com.example.fitnessapp1.shared.MealType;

import java.time.LocalDate;
import java.util.List;

public interface MealStatService {
    MealStatResponse create(AddMealStatRequest addMealStatRequest, Long mealId);
    List<MealStatResponse> searchMealStatByDate(LocalDate date);
    List<MealStatResponse> searchMealStatByUserIdAndDateAndType(MealType type);
    void delete(Long id);
}
