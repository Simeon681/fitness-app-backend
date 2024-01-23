package com.example.fitnessapp1.repository;

import com.example.fitnessapp1.entity.ActivityStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ActivityStatRepository extends JpaRepository<ActivityStat, Long> {
    List<ActivityStat> searchAllByDate(LocalDate date);
}
