package com.example.fitnessapp1.mapper;

import com.example.fitnessapp1.entity.ActivityStat;
import com.example.fitnessapp1.resource.request.ActivityStatResource;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ActivityStatMapper {
    ActivityStatMapper ACTIVITY_STAT_MAPPER = Mappers.getMapper(ActivityStatMapper.class);
    ActivityStat fromActivityStatResource(ActivityStatResource activityStatResource);
    ActivityStatResource toActivityStatResource(ActivityStat activityStat);
}
