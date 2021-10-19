package com.kristovski.gbapp.api.user;

import com.kristovski.gbapp.api.mapper.UserMapper;
import com.kristovski.gbapp.booking.BookingService;
import com.kristovski.gbapp.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/details/{id}")
    public UserDto getDetails(@PathVariable @Min(1) Long id){
        return mapper.mapToUserDto(userService.getById(id));
    }
}
