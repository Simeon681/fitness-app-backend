package com.example.fitnessapp1.mapper;

import com.example.fitnessapp1.entity.User;
import com.example.fitnessapp1.resource.request.RegisterUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.example.fitnessapp1.resource.response.LoginResponse;

@Mapper
public interface UserMapper {
    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);
    User fromUserRequest(RegisterUserRequest userRequest);
    LoginResponse toRegisterUserResponse(User user);
}
