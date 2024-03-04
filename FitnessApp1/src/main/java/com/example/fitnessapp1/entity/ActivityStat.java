package com.example.fitnessapp1.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "activity_stat")
@Data
public class ActivityStat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;

    @Column(name = "date", nullable = false, columnDefinition = "DATE")
    private LocalDate date;

    @Column(name = "steps", columnDefinition = "INTEGER")
    private Integer steps;

    @Column(name = "calories_consumed", columnDefinition = "INTEGER")
    private Integer calories;

    @Column(name = "protein_comsumed", columnDefinition = "FLOAT")
    private Float protein;

    @Column(name = "carbs_comsumed", columnDefinition = "FLOAT")
    private Float carbs;

    @Column(name = "fat_comsumed", columnDefinition = "FLOAT")
    private Float fat;

    @Column(name = "water_comsumed", columnDefinition = "NUMERIC(3, 2)")
    private Float water;
}
