package com.kristovski.gbapp.booking;

import com.kristovski.gbapp.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookingController {

    private BookingService bookingService;
    private UserServiceImpl userService;

    @Autowired
    public BookingController(BookingService bookingService, UserServiceImpl userService) {
        this.bookingService = bookingService;
        this.userService = userService;
    }

    @GetMapping("/booking")
    public String calendar(Model model) {
        model.addAttribute("booking", new Booking());
        return "booking";
    }

    @PostMapping
    public String saveBooking(Booking booking, Model model){
        model.addAttribute("booking", booking);
        return "/panel/bookings";
    }


    @GetMapping("/panel/deleteBooking/{id}")
    public String deleteBooking(@PathVariable(value = "id") Long id) {
        bookingService.deleteBookingById(id);
        return "redirect:/panel/bookings";
    }




}
