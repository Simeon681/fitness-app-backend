package com.example.fitnessapp1.resource.request;

import com.example.fitnessapp1.shared.MealType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddMealStatRequest {
    @NotNull
    @DecimalMin(value = "0.1")
    private Float portion;

    @NotNull
    private MealType type;
}
