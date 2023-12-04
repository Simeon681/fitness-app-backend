package com.example.fitnessapp1.entity;

import com.example.fitnessapp1.shared.MealType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "meal_stat")
@Data
public class MealStat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "meal_id", nullable = false, referencedColumnName = "id")
    private Meal meal;

    @Column(name = "date", nullable = false, columnDefinition = "DATE")
    private Date date;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, columnDefinition = "VARCHAR(9)")
    private MealType type;
}
