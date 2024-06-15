package com.example.fitnessapp1.controller;

import com.example.fitnessapp1.resource.request.WeightRequest;
import com.example.fitnessapp1.resource.response.WeightChangeResponse;
import com.example.fitnessapp1.service.WeightChangeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/weight-change")
@RequiredArgsConstructor
public class WeightChangeController {
    private final WeightChangeService weightChangeService;

    @PatchMapping
    public ResponseEntity<?> updateWeight(@Valid @RequestBody WeightRequest weightRequest) {
        weightChangeService.updateWeight(weightRequest);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<WeightChangeResponse>> getWeightChanges() {
        System.out.println(weightChangeService.getWeightChanges());
        return ResponseEntity.ok(weightChangeService.getWeightChanges());
    }
}
