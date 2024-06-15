package com.example.fitnessapp1.entity;

import com.example.fitnessapp1.shared.MealType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "meal_stat")
@Data
public class MealStat {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "meal_id", nullable = false, referencedColumnName = "id")
    private Meal meal;

    @Column(name = "portion", nullable = false)
    private Float portion;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private MealType type;

    @Column(name = "date", nullable = false, columnDefinition = "DATE")
    private LocalDate date;
}
