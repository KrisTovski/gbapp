package com.kristovski.gbapp.booking;

import com.kristovski.gbapp.date.MyDate;
import com.kristovski.gbapp.user.User;
import com.kristovski.gbapp.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Controller
public class BookingController {

    private final String REDIRECT = "redirect:/";

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
        return REDIRECT + "panel/bookings";
    }

    @GetMapping("/bookingtime")
    public String bookTime(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
     //   if(user != null){
            Booking booking = new Booking();
            booking.setUser(user);
            MyDate myDate = (MyDate) session.getAttribute("choosedate");

            List<Booking> bookingList;

            if(myDate == null){

                LocalDate now = LocalDate.now();
                booking.setDate(now);
                bookingList = bookingService.findBookingsByDate(now);
                model.addAttribute("choosedate", new MyDate(now));
            } else {
                booking.setDate(myDate.getDate());
                bookingList = bookingService.findBookingsByDate(myDate.getDate());
                model.addAttribute("choosedate", myDate);
            }

            session.setAttribute("booking", booking);

            model.addAttribute("user", user);
            model.addAttribute("bookingList", bookingList);

            return "bookingtime";
    //    }
   //    return REDIRECT;
    }
    @PostMapping("/bookingtime/changedate")
    public String changedate(Model model, HttpSession session, @ModelAttribute MyDate date){

        if(date.getDate() != null){
            session.setAttribute("choosedate", date);
        }
        return REDIRECT + "bookingtime";

    }




}
