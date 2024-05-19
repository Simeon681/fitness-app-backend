package com.example.fitnessapp1.service;

import com.example.fitnessapp1.resource.request.HeightRequest;
import com.example.fitnessapp1.resource.response.HeightChangeResponse;
import jakarta.transaction.Transactional;

import java.util.List;

public interface HeightChangeService {
    void saveHeight(Float height, String userId);

    @Transactional
    void updateHeight(HeightRequest heightRequest);

    List<HeightChangeResponse> getHeightChanges();
}
