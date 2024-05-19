package com.example.fitnessapp1.resource.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class WeightChangeResponse {
    private LocalDate date;
    private Float weight;
}