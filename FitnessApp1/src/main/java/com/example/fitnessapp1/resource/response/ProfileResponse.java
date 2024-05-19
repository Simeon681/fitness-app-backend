package com.example.fitnessapp1.resource.response;

import lombok.Data;

@Data
public class ProfileResponse {
    private Integer goalCalories;
    private Float goalProtein;
    private Float goalCarbs;
    private Float goalFat;
    private Float goalWater;
    private Integer goalSteps;
}
