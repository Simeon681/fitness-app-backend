package com.example.fitnessapp1.service;

import com.example.fitnessapp1.resource.request.WeightRequest;
import com.example.fitnessapp1.resource.response.WeightChangeResponse;
import jakarta.transaction.Transactional;

import java.util.List;

public interface WeightChangeService {
    void saveWeight(Float weight, String userId);

    @Transactional
    void updateWeight(WeightRequest weightRequest);

    List<WeightChangeResponse> getWeightChanges();
}
