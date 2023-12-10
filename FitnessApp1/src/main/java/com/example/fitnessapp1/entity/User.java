package com.example.fitnessapp1.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", unique = true, nullable = false, columnDefinition = "VARCHAR(16)")
    private String username;

    @Column(name = "password", nullable = false, columnDefinition = "VARCHAR(32)")
    private String password;
}
