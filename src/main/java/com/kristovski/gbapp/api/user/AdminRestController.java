package com.kristovski.gbapp.api.user;

import com.kristovski.gbapp.api.mapper.UserMapper;
import com.kristovski.gbapp.booking.BookingService;
import com.kristovski.gbapp.user.User;
import com.kristovski.gbapp.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/panel")
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
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> userList = userService.findAll();
        List<UserDto> collect = userList.stream()
                .map(user -> mapper.mapToUserDto(user))
                .collect(Collectors.toList());
        return new ResponseEntity<>(collect, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        User user = userService.getById(id);
        return new ResponseEntity<>(mapper.mapToUserDto(user), HttpStatus.OK);
    }

    @PatchMapping("/user/{id}")
    public ResponseEntity<UserDto> updateUserById(@PathVariable Long id, @RequestBody UserDto userDto) {
        userDto.setUserUrl("/api/panel/user/" + id);
        User user = mapper.mapToUser(userDto);
        userService.patch(id, user);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
