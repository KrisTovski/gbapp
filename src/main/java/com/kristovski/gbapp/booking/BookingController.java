package com.kristovski.gbapp.booking;

import com.kristovski.gbapp.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {

    private BookingService bookingService;
    private UserServiceImpl userService;

    @Autowired
    public BookingController(BookingService bookingService, UserServiceImpl userService) {
        this.bookingService = bookingService;
        this.userService = userService;
    }
}
