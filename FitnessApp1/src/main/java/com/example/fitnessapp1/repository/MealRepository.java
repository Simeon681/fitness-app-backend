package com.example.fitnessapp1.repository;

import com.example.fitnessapp1.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
    Optional<Meal> findByName(String name);

    List<Meal> findByNameContaining(String mealName);
}
