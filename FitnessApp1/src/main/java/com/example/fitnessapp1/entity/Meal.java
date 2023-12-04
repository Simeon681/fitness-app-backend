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

    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(32)")
    private String name;

    @Column(name = "calories", nullable = false, columnDefinition = "INTEGER")
    private Integer calories;
}
