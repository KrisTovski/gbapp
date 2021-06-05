package com.kristovski.gbapp.user;

import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    UserUpdateDto userUpdateDto(User user);

    User user(UserUpdateDto userUpdateDto);
}
