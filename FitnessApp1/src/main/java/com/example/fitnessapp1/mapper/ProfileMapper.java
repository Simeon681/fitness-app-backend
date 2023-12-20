package com.example.fitnessapp1.mapper;

import com.example.fitnessapp1.entity.Profile;
import com.example.fitnessapp1.resource.request.ProfileResource;
import com.example.fitnessapp1.resource.request.RegisterUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProfileMapper {
    ProfileMapper PROFILE_MAPPER = Mappers.getMapper(ProfileMapper.class);
    Profile fromProfileRequest(RegisterUserRequest profileRequest);
    ProfileResource toProfileResource(Profile profile);
}
