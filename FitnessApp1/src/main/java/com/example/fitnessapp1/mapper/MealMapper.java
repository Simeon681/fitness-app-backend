package com.example.fitnessapp1.mapper;

import com.example.fitnessapp1.entity.Meal;
import com.example.fitnessapp1.resource.request.MealRequest;
import com.example.fitnessapp1.resource.response.MealResponse;
import org.mapstruct.factory.Mappers;

public interface MealMapper {
    MealMapper MEAL_MAPPER = Mappers.getMapper(MealMapper.class);
    Meal fromMealRequest(MealRequest mealRequest);
    MealResponse toMealResponse(Meal meal);
}
