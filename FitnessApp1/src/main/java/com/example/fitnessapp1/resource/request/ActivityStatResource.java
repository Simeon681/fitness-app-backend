package com.example.fitnessapp1.resource.request;

import com.example.fitnessapp1.entity.Meal;
import com.example.fitnessapp1.entity.MealStat;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class ActivityStatResource {
    @Min(0)
    private Integer steps;

    private Integer calories;

    private Float protein;

    private Float carbs;

    private Float fat;

    private Float water;

    public Integer getCalories(MealStat mealStat) {
        if (this.calories == null) {
            this.calories = 0;
        }

        Meal meal = mealStat.getMeal();

        this.calories += Math.round(meal.getCalories().floatValue() * mealStat.getPortion() / 100);

        return this.calories;
    }

    public Float getProtein(MealStat mealStat) {
        if (this.protein == null) {
            this.protein = 0.0f;
        }

        Meal meal = mealStat.getMeal();

        this.protein += meal.getProtein() * mealStat.getPortion() / 100;

        return this.protein;
    }

    public Float getCarbs(MealStat mealStat) {
        if (this.carbs == null) {
            this.carbs = 0.0f;
        }

        Meal meal = mealStat.getMeal();

        this.carbs += meal.getCarbs() * mealStat.getPortion() / 100;

        return this.carbs;
    }

    public Float getFat(MealStat mealStat) {
        if (this.fat == null) {
            this.fat = 0.0f;
        }

        Meal meal = mealStat.getMeal();

        this.fat += meal.getFat() * mealStat.getPortion() / 100;

        return this.fat;
    }
}
