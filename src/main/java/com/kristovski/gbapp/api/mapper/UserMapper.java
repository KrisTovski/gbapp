package com.kristovski.gbapp.api.mapper;

import com.kristovski.gbapp.api.user.UserDto;
import com.kristovski.gbapp.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto mapToUserDto(User user);

    User mapToUser(UserDto dto);

}
