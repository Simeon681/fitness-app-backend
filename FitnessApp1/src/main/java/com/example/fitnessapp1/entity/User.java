package com.example.fitnessapp1.entity;

import com.example.fitnessapp1.entity.Profile;
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

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false, referencedColumnName = "id")
    private Profile profile;

    @Column(name = "username", unique = true, nullable = false, columnDefinition = "VARCHAR(16)")
    private String username;

    @Column(name = "password", nullable = false, columnDefinition = "VARCHAR(32)")
    private String password;
}
