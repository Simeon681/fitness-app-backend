package com.example.fitnessapp1.resource.request;

import com.example.fitnessapp1.shared.ActivityLevel;
import com.example.fitnessapp1.shared.Gender;
import com.example.fitnessapp1.shared.WeightGoal;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProfileResource {
    @NotNull
    private String dateOfBirth;

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
    private ActivityLevel activityLevel;

    @NotNull
    private WeightGoal weightGoal;

    @NotNull
    @Min(0)
    private int goalSteps;
}
