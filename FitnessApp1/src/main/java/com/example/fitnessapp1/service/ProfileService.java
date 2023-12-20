package com.example.fitnessapp1.service;


import com.example.fitnessapp1.resource.request.ProfileResource;

public interface ProfileService {
    ProfileResource getById(Long id);
    ProfileResource update(ProfileResource profileResource, Long id);
}
