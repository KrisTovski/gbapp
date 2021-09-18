package com.kristovski.gbapp.booking;

import com.kristovski.gbapp.Room.Room;
import com.kristovski.gbapp.Room.RoomService;
import com.kristovski.gbapp.date.MyDate;
import com.kristovski.gbapp.security.IAuthenticationFacade;
import com.kristovski.gbapp.user.User;
import com.kristovski.gbapp.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class BookingController {

    private String REDIRECT = "redirect:/";

    private BookingService bookingService;
    private UserServiceImpl userService;
    private RoomService roomService;
    private IAuthenticationFacade authenticationFacade;

    @Autowired
    public BookingController(BookingService bookingService,
                             UserServiceImpl userService,
                             RoomService roomService,
                             IAuthenticationFacade authenticationFacade) {
        this.bookingService = bookingService;
        this.userService = userService;
        this.roomService = roomService;
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

    @GetMapping("/selectroom")
    public String selectRoom(Model model) {
        User user = getUser();

        model.addAttribute("user", user);
        model.addAttribute("booking", new Booking());
        model.addAttribute("roomList", roomService.findAll());
        return "selectRoom";
    }

    @GetMapping({"/bookingtime/{roomId}", "/bookingtime"})
    public String bookTime(HttpSession session, Model model, @PathVariable(required = false) Long roomId) {


        User user = getUser();
        Booking booking = new Booking();

        if (roomId == null) {
            return REDIRECT + "selectroom";
        }

        if (roomId != 0) {
            Room roomById = roomService.findRoomById(roomId);
            model.addAttribute("roomName", roomById.getName());
            booking.setRoom(roomById);
            session.setAttribute("booking", booking);
        }


        booking.setUser(user);
        MyDate myDate = (MyDate) session.getAttribute("choosedate");

        List<Booking> bookingList;

        if (myDate == null) {

            LocalDate now = LocalDate.now();
            booking.setDate(now);
            bookingList = bookingService.findBookingsByDate(now, roomService.findRoomById(roomId));
            model.addAttribute("choosedate", new MyDate(now));
        } else {
            booking.setDate(myDate.getDate());
            bookingList = bookingService.findBookingsByDate(myDate.getDate(), roomService.findRoomById(roomId));
            model.addAttribute("choosedate", myDate);
        }

        model.addAttribute("user", user);
        model.addAttribute("bookingList", bookingList);


        return "bookingtime";

    }

    @PostMapping("/bookingtime/changedate")
    public String changedate(HttpSession session,
                             @ModelAttribute MyDate date
    ) {

        Booking booking = (Booking) session.getAttribute("booking");

        Long id = booking.getRoom().getId();

        if (date.getDate() != null) {
            session.setAttribute("choosedate", date);
        }


        if (id == 1) {
            return REDIRECT + "bookingtime/1";
        }
        if (id == 2) {
            return REDIRECT + "bookingtime/2";
        }

        return REDIRECT + "bookingtime/0";

    }

    @GetMapping("/bookingconfirmation/{time}")
    public String bookingConfirmation(HttpSession session, Model model, @PathVariable String time) {

        User user = getUser();
        Booking booking = (Booking) session.getAttribute("booking");


        booking.setStart(LocalTime.parse(time));

        if (bookingService.bookingExists(booking.getDate(), booking.getStart(), booking.getRoom())) {
            session.removeAttribute("booking");
            return REDIRECT;
        }
        model.addAttribute("user", user);
        model.addAttribute("booking", booking);
        return "bookingconfirmation";
    }

    @PostMapping("bookingconfirmation/savebooking")
    public String bookingConfirmation(HttpSession session) {

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
