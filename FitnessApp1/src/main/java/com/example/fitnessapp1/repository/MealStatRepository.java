package com.example.fitnessapp1.repository;

import com.example.fitnessapp1.entity.MealStat;
import com.example.fitnessapp1.shared.MealType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MealStatRepository extends JpaRepository<MealStat, Long> {
    List<MealStat> findByUserIdAndDate(Long userId, LocalDate date);
    List<MealStat> findByUserIdAndDateAndType(Long userId, LocalDate date, MealType type);
    void deleteByUserId(Long id);
}
