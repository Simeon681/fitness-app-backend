package com.example.fitnessapp1.service;


import com.example.fitnessapp1.entity.ActivityStat;
import com.example.fitnessapp1.resource.request.ActivityStatResource;
import jakarta.annotation.Nullable;

import java.time.LocalDate;
import java.util.List;

public interface ActivityStatService {
    ActivityStatResource create(ActivityStatResource activityStatResource, @Nullable Long userId);
    void executeDailyTask();
    List<ActivityStat> searchAllByDate(LocalDate date);
    ActivityStat findByUserIdAndDate();
    ActivityStatResource update(ActivityStatResource activityStatResource);
}
