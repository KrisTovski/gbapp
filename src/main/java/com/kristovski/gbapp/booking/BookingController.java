package com.kristovski.gbapp.booking;

import com.kristovski.gbapp.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BookingController {

    private BookingService bookingService;
    private UserServiceImpl userService;

    @Autowired
    public BookingController(BookingService bookingService, UserServiceImpl userService) {
        this.bookingService = bookingService;
        this.userService = userService;
    }

    @GetMapping("/panel/deleteBooking/{id}")
    public String deleteBooking(@PathVariable(value = "id") Long id) {
        bookingService.deleteBookingById(id);
        return "redirect:/panel/bookings";
    }

}
