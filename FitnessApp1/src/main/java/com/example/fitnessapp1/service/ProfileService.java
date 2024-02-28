package com.example.fitnessapp1.service;


import com.example.fitnessapp1.resource.request.ProfileResource;
import com.example.fitnessapp1.resource.response.ProfileResponse;

public interface ProfileService {
    ProfileResource update(ProfileResource profileResource);
    ProfileResponse getProfile();
}
