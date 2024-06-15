package com.example.fitnessapp1.repository;

import com.example.fitnessapp1.entity.WeightChange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface WeightChangeRepository extends JpaRepository<WeightChange, String> {
    boolean existsByUserIdAndDate(String userId, LocalDate date);
    WeightChange findByUserIdAndDate(String userId, LocalDate date);

    @Query(value = "SELECT * FROM weight_changes WHERE user_id = ?1 ORDER BY date LIMIT 7", nativeQuery = true)
    List<WeightChange> getTopSevenById(String userId);
}
