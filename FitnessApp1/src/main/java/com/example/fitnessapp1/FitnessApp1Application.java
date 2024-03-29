package com.example.fitnessapp1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FitnessApp1Application {
    public static void main(String[] args) {
        SpringApplication.run(FitnessApp1Application.class, args);
    }
}
