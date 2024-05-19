package com.example.fitnessapp1.resource.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class HeightChangeResponse {
    private LocalDate date;
    private Float height;
}
