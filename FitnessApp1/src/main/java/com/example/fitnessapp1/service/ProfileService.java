package com.example.fitnessapp1.service;


import com.example.fitnessapp1.resource.request.ProfileResource;

public interface ProfileService {
    ProfileResource update(ProfileResource profileResource, Long id);
    ProfileResource getById(Long id);
}
