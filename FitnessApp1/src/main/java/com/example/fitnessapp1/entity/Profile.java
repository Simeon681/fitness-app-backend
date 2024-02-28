package com.example.fitnessapp1.entity;

import com.example.fitnessapp1.shared.ActivityLevel;
import com.example.fitnessapp1.shared.Gender;
import com.example.fitnessapp1.shared.WeightGoal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;

    @Column(name = "date_of_birth", nullable = false, columnDefinition = "DATE")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false, columnDefinition = "VARCHAR(6)")
    private Gender gender;

    @Column(name = "height", nullable = false, columnDefinition = "NUMERIC(4, 1)")
    private Float height;

    @Column(name = "weight", nullable = false, columnDefinition = "NUMERIC(4, 1)")
    private Float weight;

    @Enumerated(EnumType.STRING)
    @Column(name = "activity_level", nullable = false, columnDefinition = "VARCHAR(20)")
    private ActivityLevel activityLevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "weight_goal", nullable = false, columnDefinition = "VARCHAR(20)")
    private WeightGoal weightGoal;

    @Column(name = "goal_calories", columnDefinition = "INTEGER")
    private Integer goalCalories;

    @Column(name = "goal_protein", columnDefinition = "FLOAT")
    private Float goalProtein;

    @Column(name = "goal_carbs", columnDefinition = "FLOAT")
    private Float goalCarbs;

    @Column(name = "goal_fat", columnDefinition = "FLOAT")
    private Float goalFat;

    @Column(name = "goal_steps", nullable = false, columnDefinition = "INTEGER")
    private Integer goalSteps;

    @Column(name = "goal_water", columnDefinition = "NUMERIC(3, 2)")
    private Float goalWater;
}