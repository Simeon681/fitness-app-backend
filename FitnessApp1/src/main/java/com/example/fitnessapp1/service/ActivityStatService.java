package com.example.fitnessapp1.service;


import com.example.fitnessapp1.resource.request.ActivityStatResource;

public interface ActivityStatService {
    ActivityStatResource create(ActivityStatResource activityStatResource, Long id);
    ActivityStatResource update(ActivityStatResource activityStatResource, Long userId, Long id);
}
