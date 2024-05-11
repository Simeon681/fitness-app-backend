package com.example.fitnessapp1.repository;

import com.example.fitnessapp1.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, String> {
}
