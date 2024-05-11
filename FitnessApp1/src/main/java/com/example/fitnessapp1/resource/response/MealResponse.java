package com.example.fitnessapp1.resource.response;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class MealResponse {
    @NotEmpty
    @Length(min = 2, max = 32)
    private String name;

    @NotNull
    @Min(0)
    private int calories;
}
