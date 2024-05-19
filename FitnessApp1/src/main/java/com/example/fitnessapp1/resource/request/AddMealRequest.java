package com.example.fitnessapp1.resource.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class AddMealRequest {
    @NotEmpty
    @Length(min = 2, max = 32)
    private String name;

    @NotNull
    @Min(0)
    private Integer calories;

    @NotNull
    @DecimalMin(value = "0.0")
    @DecimalMax(value = "100.0")
    private Float protein;

    @NotNull
    @DecimalMin(value = "0.0")
    @DecimalMax(value = "100.0")
    private Float carbs;

    @NotNull
    @DecimalMin(value = "0.0")
    @DecimalMax(value = "100.0")
    private Float fat;
}
