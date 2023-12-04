package com.example.fitnessapp1.resource.response;

import com.example.fitnessapp1.shared.MealType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class MealResponse {
    private Long id;

    @NotEmpty
    @Length(min = 2, max = 32)
    private String name;

    @NotNull
    @Min(0)
    private int calories;

    @NotNull
    private MealType type;


}
