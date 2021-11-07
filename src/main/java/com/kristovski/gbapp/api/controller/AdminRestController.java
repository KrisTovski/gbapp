package com.kristovski.gbapp.api.controller;

import com.kristovski.gbapp.api.mapper.UserMapper;
import com.kristovski.gbapp.api.user.UserDto;
import com.kristovski.gbapp.booking.BookingService;
import com.kristovski.gbapp.user.User;
import com.kristovski.gbapp.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin-panel")
public class AdminRestController {

    private UserServiceImpl userService;
    private BookingService bookingService;
    private UserMapper mapper;

    @Autowired
    public AdminRestController(UserServiceImpl userService, BookingService bookingService, UserMapper mapper) {
        this.userService = userService;
        this.bookingService = bookingService;
        this.mapper = mapper;
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAllUsers() {
        List<User> userList = userService.findAll();
        List<UserDto> collect = userList.stream()
                .map(user -> mapper.mapToUserDto(user))
                .collect(Collectors.toList());
        return collect;
    }

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserById(@PathVariable Long id) {
        User user = userService.getById(id);
        return mapper.mapToUserDto(user);
    }

    @PatchMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto updateUserById(@PathVariable Long id, @RequestBody UserDto userDto) {
        userDto.setUserUrl("/api/admin-panel/user/" + id);
        User user = mapper.mapToUser(userDto);
        userService.patch(id, user);
        return userDto;
    }

    @DeleteMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteById(id);
    }

}
