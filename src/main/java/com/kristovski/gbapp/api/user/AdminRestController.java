package com.kristovski.gbapp.api.user;

import com.kristovski.gbapp.api.mapper.UserMapper;
import com.kristovski.gbapp.booking.BookingService;
import com.kristovski.gbapp.user.User;
import com.kristovski.gbapp.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
