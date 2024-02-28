package com.example.fitnessapp1.resource.response;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MealStatResponse {
    private Long id;

    @NotNull
    @DecimalMin(value = "0.1")
    @DecimalMax(value = "50.0", inclusive = false)
    private Float portion;
}
