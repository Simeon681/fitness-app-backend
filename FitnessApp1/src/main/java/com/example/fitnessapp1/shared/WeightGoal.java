package com.example.fitnessapp1.shared;

public enum WeightGoal {
    LOSE("Lose Weight"),
    MAINTAIN("Maintain Weight"),
    GAIN("Gain Weight");

    private final String displayName;

    WeightGoal(String displayName) {
        this.displayName = displayName;
    }
}
