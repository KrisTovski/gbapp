package com.kristovski.gbapp.api.mapper;

import com.kristovski.gbapp.api.user.UserDto;
import com.kristovski.gbapp.user.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class UserMapperTest {

    private static final LocalDateTime CREATE_TIME = LocalDateTime.of(2020, 9, 10, 12, 38, 45);
    private static final LocalDateTime UPDATE_TIME = LocalDateTime.of(2021, 5, 4, 13, 25, 18);

    @Test
    public void shouldMapUserToDto() {
        // given
        User user = new User(1L, "MyLogin", "John", "Covalsky", "john@gmail.com", "pass", true,
                CREATE_TIME, UPDATE_TIME, false);
        // when
        UserDto userDto = UserMapper.INSTANCE.mapToUserDto(user);
        // then
        assertThat(userDto).isNotNull();
        assertThat(userDto.getId()).isEqualTo(1L);
        assertThat(userDto.getLogin()).isEqualTo("MyLogin");
        assertThat(userDto.getFirstName()).isEqualTo("John");
        assertThat(userDto.getLastName()).isEqualTo("Covalsky");
        assertThat(userDto.getEmail()).isEqualTo("john@gmail.com");
        assertThat(userDto.getPassword()).isEqualTo("pass");
        assertThat(userDto.isEnable()).isEqualTo(true);
        assertThat(userDto.getCreateTime()).isEqualTo(CREATE_TIME);
        assertThat(userDto.getUpdateTime()).isEqualTo(UPDATE_TIME);
        assertThat(userDto.isLocked()).isEqualTo(false);
    }

    @Test
    public void shouldMapDtoToUser() {
        // given
        UserDto dto = new UserDto(1L, "MyLogin", "John", "Covalsky", "john@gmail.com", "pass", true,
                CREATE_TIME, UPDATE_TIME, false, "/api/panel/user/1");
        // when
        User user = UserMapper.INSTANCE.mapToUser(dto);
        // then
        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getLogin()).isEqualTo("MyLogin");
        assertThat(user.getFirstName()).isEqualTo("John");
        assertThat(user.getLastName()).isEqualTo("Covalsky");
        assertThat(user.getEmail()).isEqualTo("john@gmail.com");
        assertThat(user.getPassword()).isEqualTo("pass");
        assertThat(user.isEnable()).isEqualTo(true);
        assertThat(user.getCreateTime()).isEqualTo(CREATE_TIME);
        assertThat(user.getUpdateTime()).isEqualTo(UPDATE_TIME);
        assertThat(user.isLocked()).isEqualTo(false);
    }
}