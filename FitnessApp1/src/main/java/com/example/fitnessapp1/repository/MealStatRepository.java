package com.example.fitnessapp1.repository;

import com.example.fitnessapp1.entity.MealStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealStatRepository extends JpaRepository<MealStat, Long> {
}
