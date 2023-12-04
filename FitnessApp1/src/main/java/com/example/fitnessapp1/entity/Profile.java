package com.example.fitnessapp1.entity;

import com.example.fitnessapp1.shared.Gender;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "profile")
@Data
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "birthday", nullable = false, columnDefinition = "DATE")
    private Date birthday;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false, columnDefinition = "VARCHAR(6)")
    private Gender gender;

    @Column(name = "height", nullable = false, columnDefinition = "NUMERIC(4, 1)")
    private Float height;

    @Column(name = "weight", nullable = false, columnDefinition = "NUMERIC(4, 1)")
    private Float weight;

    @Column(name = "BMI", nullable = false, columnDefinition = "NUMERIC(3, 1)")
    private Float BMI;

    @Column(name = "goal_calories", nullable = false, columnDefinition = "INTEGER")
    private Integer goalCalories;

    @Column(name = "goal_weight", nullable = false, columnDefinition = "NUMERIC(4, 1)")
    private Float goalWeight;

    @Column(name = "goal_steps", nullable = false, columnDefinition = "INTEGER")
    private Integer goalSteps;

    @Column(name = "goal_water", nullable = false, columnDefinition = "NUMERIC(3, 1)")
    private Float goalWater;
}
