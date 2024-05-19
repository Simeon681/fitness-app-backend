package com.example.fitnessapp1.mapper;

import com.example.fitnessapp1.entity.WeightChange;
import com.example.fitnessapp1.resource.response.WeightChangeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface WeightChangeMapper {
    WeightChangeMapper WEIGHT_CHANGE_MAPPER = Mappers.getMapper(WeightChangeMapper.class);
    List<WeightChangeResponse> toResponse(List<WeightChange> weightChanges);
}