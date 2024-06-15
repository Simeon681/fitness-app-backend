package com.example.fitnessapp1.resource.request;

import com.example.fitnessapp1.shared.ActivityLevel;
import com.example.fitnessapp1.shared.Gender;
import com.example.fitnessapp1.shared.WeightGoal;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Data
public class RegisterUserRequest {
    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Length(min = 6, max = 32)
    private String username;

    @NotEmpty
    @Length(min = 8, max = 32)
    private String password;

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

    private Integer goalCalories;

    private Float goalProtein;

    private Float goalCarbs;

    private Float goalFat;

    private Float goalWater;

    @NotNull
    @Min(0)
    private int goalSteps;

    public Integer getGoalCalories() {
        LocalDate dateOfBirth = LocalDate.parse(this.dateOfBirth, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        int age = Period.between(dateOfBirth, LocalDate.now()).getYears();
        float BMR = getBmr(age);

        if (weightGoal.equals(WeightGoal.LOSE)) {
            this.goalCalories = Math.round(BMR - 500);
        } else if (weightGoal.equals(WeightGoal.GAIN)) {
            this.goalCalories = Math.round(BMR + 500);
        } else {
            this.goalCalories = Math.round(BMR);
        }

        return goalCalories;
    }

    // in grams
    public Float getGoalProtein() {
        if (weightGoal.equals(WeightGoal.LOSE)) {
            this.goalProtein = goalCalories.floatValue() * 0.45f / 4;
        } else if (weightGoal.equals(WeightGoal.GAIN)) {
            this.goalProtein = goalCalories.floatValue() * 0.3f / 4;
        } else {
            this.goalProtein = goalCalories.floatValue() * 0.3f / 4;
        }

        return goalProtein;
    }

    public Float getGoalCarbs() {
        if (weightGoal.equals(WeightGoal.LOSE)) {
            this.goalCarbs = goalCalories.floatValue() * 0.2f / 4;
        } else if (weightGoal.equals(WeightGoal.GAIN)) {
            this.goalCarbs = goalCalories.floatValue() * 0.5f / 4;
        } else {
            this.goalCarbs = goalCalories.floatValue() * 0.4f / 4;
        }

        return goalCarbs;
    }

    public Float getGoalFat() {
        if (weightGoal.equals(WeightGoal.LOSE)) {
            this.goalFat = goalCalories.floatValue() * 0.35f / 9;
        } else if (weightGoal.equals(WeightGoal.GAIN)) {
            this.goalFat = goalCalories.floatValue() * 0.2f / 9;
        } else {
            this.goalFat = goalCalories.floatValue() * 0.3f / 9;
        }

        return goalFat;
    }

    public Float getGoalWater() { //in liters
        this.goalWater = weight * 0.035f;

        return goalWater;
    }

    private float getBmr(int age) {
        //Mifflin-St Jeor Equation
        float BMR;

        if (gender.equals(Gender.MALE)) {
            BMR = (10 * weight) + (6.25f * height) - (5 * age) + 5;
        } else {
            BMR = (10 * weight) + (6.25f * height) - (5 * age) - 161;
        }

        if (activityLevel.equals(ActivityLevel.SEDENTARY)) {
            BMR *= 1.2f;
        } else if (activityLevel.equals(ActivityLevel.LIGHTLY_ACTIVE)) {
            BMR *= 1.375f;
        } else if (activityLevel.equals(ActivityLevel.MODERATELY_ACTIVE)) {
            BMR *= 1.55f;
        } else if (activityLevel.equals(ActivityLevel.VERY_ACTIVE)) {
            BMR *= 1.725f;
        } else {
            BMR *= 1.9f;
        }

        return BMR;
    }
}
