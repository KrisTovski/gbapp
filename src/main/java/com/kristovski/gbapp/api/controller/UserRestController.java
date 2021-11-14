package com.kristovski.gbapp.api.controller;

import com.kristovski.gbapp.api.mapper.UserMapper;
import com.kristovski.gbapp.api.user.UserDto;
import com.kristovski.gbapp.booking.BookingService;
import com.kristovski.gbapp.user.User;
import com.kristovski.gbapp.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

    private UserServiceImpl userService;
    private BookingService bookingService;
    private UserMapper mapper;

    @Autowired
    public UserRestController(UserServiceImpl userService, BookingService bookingService, UserMapper mapper) {
        this.userService = userService;
        this.bookingService = bookingService;
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getById(@PathVariable @Min(1) Long id){
        return mapper.mapToUserDto(userService.getById(id));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto updateById(@PathVariable Long id, @RequestBody UserDto userDto) {
        userDto.setUserUrl("/api/user/" + id);
        User user = mapper.mapToUser(userDto);
        userService.patch(id, user);
        return userDto;
    }
}
