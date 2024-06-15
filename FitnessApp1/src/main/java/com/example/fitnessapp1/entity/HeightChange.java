package com.example.fitnessapp1.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "height_changes")
@Data
public class HeightChange {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;

    @Column(name = "date", nullable = false, columnDefinition = "DATE")
    private LocalDate date;

    @Column(name = "height", nullable = false)
    private Float height;
}
