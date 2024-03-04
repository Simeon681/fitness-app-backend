package com.example.fitnessapp1.mapper;

import com.example.fitnessapp1.entity.Meal;
import com.example.fitnessapp1.resource.request.AddMealRequest;
import com.example.fitnessapp1.resource.response.MealResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MealMapper {
    MealMapper MEAL_MAPPER = Mappers.getMapper(MealMapper.class);
    Meal fromMealRequest(AddMealRequest addMealRequest);
    MealResponse toMealResponse(Meal meal);
}
