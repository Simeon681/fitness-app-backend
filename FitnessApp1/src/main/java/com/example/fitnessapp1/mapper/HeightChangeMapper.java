package com.example.fitnessapp1.mapper;

import com.example.fitnessapp1.entity.HeightChange;
import com.example.fitnessapp1.resource.response.HeightChangeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface HeightChangeMapper {
    HeightChangeMapper HEIGHT_CHANGE_MAPPER = Mappers.getMapper(HeightChangeMapper.class);
    List<HeightChangeResponse> toResponse(List<HeightChange> heightChanges);
}
