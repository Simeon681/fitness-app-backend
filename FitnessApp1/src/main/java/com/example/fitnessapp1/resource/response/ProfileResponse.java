package com.example.fitnessapp1.resource.response;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProfileResponse {
    @NotNull
    private Integer goalCalories;

    @NotNull
    private Float goalProtein;

    @NotNull
    private Float goalCarbs;

    @NotNull
    private Float goalFat;

    @NotNull
    private Float goalWater;

    @NotNull
    @Min(0)
    private int goalSteps;
}
