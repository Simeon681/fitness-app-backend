package com.example.fitnessapp1.resource.request;

import com.example.fitnessapp1.shared.Gender;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Data
public class RegisterUserRequest {
    @NotEmpty
    @Length(min = 6, max = 32)
    private String username;

    @NotEmpty
    @Length(min = 8, max = 32)
    private String password;

    @NotNull
    private LocalDate dateOfBirth;

    @NotNull
    private Gender gender;

    @NotNull
    @DecimalMin(value = "30.0", inclusive = false)
    @DecimalMax(value = "300.0", inclusive = false)
    private float height;

    @NotNull
    @DecimalMin(value = "30.0", inclusive = false)
    @DecimalMax(value = "400.0", inclusive = false)
    private float weight;

    @NotNull
    @Min(0)
    private int goalCalories;

    @NotNull
    @DecimalMin(value = "30.0", inclusive = false)
    @DecimalMax(value = "400.0", inclusive = false)
    private float goalWeight;

    @NotNull
    @Min(0)
    private int goalSteps;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @DecimalMax(value = "10.0", inclusive = false)
    private float goalWater;
}
