package com.example.fitnessapp1.repository;

import com.example.fitnessapp1.entity.HeightChange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface HeightChangeRepository extends JpaRepository<HeightChange, String> {
    boolean existsByUserIdAndDate(String userId, LocalDate date);
    HeightChange findByUserIdAndDate(String userId, LocalDate date);

    @Query(value = "SELECT * FROM height_changes WHERE user_id = ?1 ORDER BY date LIMIT 7", nativeQuery = true)
    List<HeightChange> getTopSevenById(String userId);
}
