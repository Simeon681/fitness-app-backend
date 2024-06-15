package com.example.fitnessapp1.service;


import com.example.fitnessapp1.entity.ActivityStat;
import com.example.fitnessapp1.resource.request.ActivityStatResource;
import jakarta.annotation.Nullable;

public interface ActivityStatService {
    ActivityStatResource create(ActivityStatResource activityStatResource, @Nullable String userId);

    void executeDailyTask();

    ActivityStat findByUserIdAndDate();

    ActivityStatResource update(ActivityStatResource activityStatResource);
}
