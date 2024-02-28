package com.example.fitnessapp1.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "meal")
@Data
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true, nullable = false, columnDefinition = "VARCHAR(32)")
    private String name;

    // per 100g
    @Column(name = "calories", nullable = false, columnDefinition = "INTEGER")
    private Integer calories;

    @Column(name = "protein", nullable = false, columnDefinition = "NUMERIC(5, 2)")
    private Float protein;

    @Column(name = "carbs", nullable = false, columnDefinition = "NUMERIC(5, 2)")
    private Float carbs;

    @Column(name = "fat", nullable = false, columnDefinition = "NUMERIC(5, 2)")
    private Float fat;
}
