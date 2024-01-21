package com.example.fitnessapp1.service;

import com.example.fitnessapp1.entity.Meal;
import com.example.fitnessapp1.resource.request.AddMealRequest;
import com.example.fitnessapp1.resource.response.MealResponse;

import java.util.List;

public interface MealService {
    MealResponse create(AddMealRequest addMealRequest);
    List<Meal> getAllMeals();
    List<Meal> searchMealByName(String mealName);
    Long add(Meal meal);
    MealResponse getById(Long id);
    MealResponse getByName(String name);
    void delete(Long id);
}
