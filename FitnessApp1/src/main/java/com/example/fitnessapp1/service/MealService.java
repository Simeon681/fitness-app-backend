package com.example.fitnessapp1.service;

import com.example.fitnessapp1.entity.Meal;
import com.example.fitnessapp1.resource.request.AddMealRequest;
import com.example.fitnessapp1.resource.response.MealResponse;

import java.util.List;

public interface MealService {
    MealResponse create(AddMealRequest addMealRequest);
    List<Meal> searchMealByName(String mealName);
    Meal getById(String id);
    MealResponse update(AddMealRequest addMealRequest, String id);
    MealResponse getByName(String name);
    void delete(String id);
}
