package com.example.fitnessapp1.mapper;

import com.example.fitnessapp1.entity.User;
import com.example.fitnessapp1.resource.request.RegisterUserRequest;
import com.example.fitnessapp1.resource.response.LoginResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);
    User fromRegisterRequest(RegisterUserRequest registerRequest);
    LoginResponse toRegisterUserResponse(User user);
}
