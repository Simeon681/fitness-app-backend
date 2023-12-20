package com.example.fitnessapp1.controller;

import com.example.fitnessapp1.resource.request.ProfileResource;
import com.example.fitnessapp1.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @PatchMapping("/{id}")
    private ResponseEntity<ProfileResource> update(@Valid @RequestBody ProfileResource profileResource, @PathVariable("id") Long id) {
        return ResponseEntity.ok(profileService.update(profileResource, id));
    }

    @GetMapping("/{id}")
    private ResponseEntity<ProfileResource> getById(@PathVariable Long id) {
        return ResponseEntity.ok(profileService.getById(id));
    }
}
