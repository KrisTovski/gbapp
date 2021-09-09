package com.kristovski.gbapp.booking;

import com.kristovski.gbapp.date.MyDate;
import com.kristovski.gbapp.security.IAuthenticationFacade;
import com.kristovski.gbapp.user.User;
import com.kristovski.gbapp.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class BookingController {

    private final String REDIRECT = "redirect:/";
    private String BOOKINGCONFIRMATION = "bookingconfirmation";

    private BookingService bookingService;
    private UserServiceImpl userService;
    private IAuthenticationFacade authenticationFacade;

    @Autowired
    public BookingController(BookingService bookingService, UserServiceImpl userService, IAuthenticationFacade authenticationFacade) {
        this.bookingService = bookingService;
        this.userService = userService;
        this.authenticationFacade = authenticationFacade;
    }

    @GetMapping("/booking")
    public String calendar(Model model) {
        model.addAttribute("booking", new Booking());
        return "booking";
    }

    @PostMapping
    public String saveBooking(Booking booking, Model model) {
        model.addAttribute("booking", booking);
        return "/panel/bookings";
    }

    @GetMapping("/panel/updateBooking/{id}")
    public String updateBooking(@PathVariable(value = "id") long id, Model model) {
        Booking booking = bookingService.getBookingById(id);
        // pre-populate the form
        model.addAttribute("booking", booking);
        return "/panel/updateBookingForm";
    }

    @PostMapping("/panel/updateBooking")
    public String updateBooking(@ModelAttribute("booking") Booking booking) {
        bookingService.mergeWithExistingAndUpdate(booking);
        return "/panel/updateSuccess";
    }


    @GetMapping("/panel/deleteBooking/{id}")
    public String deleteBooking(@PathVariable(value = "id") Long id) {
        bookingService.deleteBookingById(id);
        return REDIRECT + "panel/bookings";
    }

    @GetMapping("/bookingtime")
    public String bookTime(HttpSession session, Model model) {

        User user = getUser();

        //   if(user != null){
        Booking booking = new Booking();
        booking.setUser(user);
        MyDate myDate = (MyDate) session.getAttribute("choosedate");

        List<Booking> bookingList;

        if (myDate == null) {

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
    public String changedate(HttpSession session, Model model, @ModelAttribute MyDate date) {

        if (date.getDate() != null) {
            session.setAttribute("choosedate", date);
        }
        return REDIRECT + "bookingtime";

    }

    @GetMapping("/bookingconfirmation/{time}")
    public String bookingConfirmation(HttpSession session, Model model, @PathVariable String time){

        User user = getUser();
        Booking booking = (Booking) session.getAttribute("booking");

        System.out.println(time);

       booking.setStart(LocalTime.parse(time));

        System.out.println(booking);
        System.out.println(booking.getId());
        System.out.println(booking.getDate());
        System.out.println(booking.getStart());

        if(bookingService.bookingExists(booking.getDate(), booking.getStart())){
            session.removeAttribute("booking");
            return REDIRECT;
        }
        model.addAttribute("user", user);
        model.addAttribute("booking", booking);
        return BOOKINGCONFIRMATION;
    }

    @PostMapping("bookingconfirmation/savebooking")
    public String bookingConfirmation(HttpSession session){

        Booking booking = (Booking) session.getAttribute("booking");

        bookingService.addBooking(booking);
        session.removeAttribute("booking");

        return REDIRECT;
    }



    private User getUser() {
        Authentication loggedInUser = authenticationFacade.getAuthentication();
        String userEmail = loggedInUser.getName();
        User user = userService.findByEmail(userEmail);
        return user;
    }


}
