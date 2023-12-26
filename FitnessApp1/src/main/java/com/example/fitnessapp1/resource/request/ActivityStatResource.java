package com.example.fitnessapp1.resource.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ActivityStatResource {
    @NotNull
    @Min(0)
    private Integer steps;

    @NotNull
    @Min(0)
    private Integer calories;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @DecimalMax(value = "10.0", inclusive = false)
    private Float water;
}
