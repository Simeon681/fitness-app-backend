package com.example.fitnessapp1.controller;

import com.example.fitnessapp1.resource.request.HeightRequest;
import com.example.fitnessapp1.resource.response.HeightChangeResponse;
import com.example.fitnessapp1.service.HeightChangeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/height-change")
@RequiredArgsConstructor
public class HeightChangeController {
    private final HeightChangeService heightChangeService;

    @PatchMapping
    public ResponseEntity<?> updateHeight(@Valid @RequestBody HeightRequest heightRequest) {
        heightChangeService.updateHeight(heightRequest);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<HeightChangeResponse>> getHeightChanges() {
        System.out.println(heightChangeService.getHeightChanges());
        return ResponseEntity.ok(heightChangeService.getHeightChanges());
    }
}
