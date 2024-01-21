package com.example.fitnessapp1.mapper;

import com.example.fitnessapp1.entity.MealStat;
import com.example.fitnessapp1.resource.request.AddMealStatRequest;
import com.example.fitnessapp1.resource.response.MealStatResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MealStatMapper {
    MealStatMapper MEAL_STAT_MAPPER = Mappers.getMapper(MealStatMapper.class);
    MealStat fromMealStatRequest(AddMealStatRequest addMealStatRequest);
    MealStatResponse toMealStatResponse(MealStat mealStat);
}
