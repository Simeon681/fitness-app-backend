package com.example.fitnessapp1.service;


import com.example.fitnessapp1.entity.ActivityStat;
import com.example.fitnessapp1.resource.request.ActivityStatResource;

import java.time.LocalDate;
import java.util.List;

public interface ActivityStatService {
    ActivityStatResource create(ActivityStatResource activityStatResource, Long id);
    List<ActivityStat> searchAllByDate(LocalDate date);
    ActivityStatResource update(ActivityStatResource activityStatResource, Long userId, Long id);
}
