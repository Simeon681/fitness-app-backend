package com.example.fitnessapp1.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "activity_stat")
@Data
public class ActivityStat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(name = "date", nullable = false, unique = true, columnDefinition = "VARCHAR(11)")
    private String date;

    @Column(name = "steps", nullable = false, columnDefinition = "INTEGER")
    private Integer steps;

    @Column(name = "calories_consumed", nullable = false, columnDefinition = "INTEGER")
    private Integer calories;

    @Column(name = "water", nullable = false, columnDefinition = "NUMERIC(3, 1)")
    private Float water;
}
