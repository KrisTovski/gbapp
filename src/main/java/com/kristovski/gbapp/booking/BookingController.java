package com.kristovski.gbapp.booking;

import com.kristovski.gbapp.room.RoomService;
import com.kristovski.gbapp.user.UserServiceImpl;
import liquibase.pro.packaged.A;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class BookingController {

    private BookingService bookingService;
    private BookingAvailabilityService bookingAvailabilityService;
    private UserServiceImpl userService;
    private RoomService roomService;


    @GetMapping("/booking")
    public String getAllBookings(Model model) {
        Model booking = model.addAttribute("booking", bookingService.findAll());
        System.out.println(booking.toString());
        return "booking";
    }
}
