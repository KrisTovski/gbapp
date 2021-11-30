package com.kristovski.gbapp.booking;

import com.kristovski.gbapp.room.Room;
import com.kristovski.gbapp.room.RoomService;
import com.kristovski.gbapp.date.MyDate;
import com.kristovski.gbapp.security.IAuthenticationFacade;
import com.kristovski.gbapp.user.User;
import com.kristovski.gbapp.user.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private static final int GYM = 1;
    private static final int CARDIO = 2;

    Logger log = LoggerFactory.getLogger(this.getClass());

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

    @PostMapping
    public String save(Booking booking, Model model) {
        model.addAttribute("booking", booking);
        return "panel/bookings";
    }

    @GetMapping("/panel/update/booking/{id}")
    public String update(@PathVariable(value = "id") long id, Model model) {
        Booking booking = bookingService.getById(id);
        // pre-populate the form
        model.addAttribute("booking", booking);
        return "panel/updateBookingForm";
    }

    @PostMapping("/panel/update/booking")
    public String update(@ModelAttribute("booking") Booking booking) {
        try {
            bookingService.mergeWithExistingAndUpdate(booking);
        } catch (Exception e) {
            return "errorPage";
        }
        return "panel/updateSuccess";
    }

    @GetMapping("/panel/delete/booking/{id}/confirmation")
    public String deleteConfirmation(@PathVariable(value = "id") Long id, Model model) {
        Booking booking = bookingService.getById(id);
        model.addAttribute("booking", booking);
        return "deleteBookingConfirmationByAdmin";
    }


    @GetMapping("/panel/delete/booking/{id}")
    public String delete(@PathVariable(value = "id") Long id) {

        log.debug("Delete booking by Id started");
        User user = userService.getAuthenticatedUser();
        Long userId = user.getId();


        try {
            bookingService.deleteById(id);
            log.info("Booking with ID " + id + " was deleted.");
            if (userService.isAdmin()) {
                return REDIRECT + "panel/bookings";
            }
            return REDIRECT + "panel/user/" + userId + "/bookings";

        } catch (Exception e) {
            log.error("Unable to delete booking with ID: " + id + ", message: " + e.getMessage(), e);
            return "errorPage";
        }
    }

    @GetMapping("/")
    public String selectRoom(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "loginForm";
        }

        User user = userService.getAuthenticatedUser();

        model.addAttribute("user", user);
        model.addAttribute("booking", new Booking());
        model.addAttribute("roomList", roomService.findAll());
        return "selectRoom";
    }

    @GetMapping({"/bookingtime/{roomId}", "/bookingtime"})
    public String bookTime(HttpSession session, Model model, @PathVariable(required = false) Long roomId) {


        User user = userService.getAuthenticatedUser();
        Booking booking = new Booking();

        if (roomId == null || roomId < 1 || roomId > 2) {
            return REDIRECT;
        }

        Room room = roomService.findById(roomId);

        if (roomId != 0) {

            model.addAttribute("roomName", room.getName());
            booking.setRoom(room);
            session.setAttribute("booking", booking);
        }

        booking.setUser(user);
        MyDate myDate = (MyDate) session.getAttribute("choosedate");

        List<Booking> bookingList;
        List<Integer> availablePlacesList;
        List<List<User>> usersInRoom;

        if (myDate == null) {

            LocalDate now = LocalDate.now();
            booking.setDate(now);
            bookingList = bookingService.findByDate(now, room);
            availablePlacesList = bookingService.availablePlacesInRoom(now, room);
            usersInRoom = bookingService.usersInRoom(now, room);
            model.addAttribute("choosedate", new MyDate(now));
        } else {
            booking.setDate(myDate.getDate());
            bookingList = bookingService.findByDate(myDate.getDate(), room);
            availablePlacesList = bookingService.availablePlacesInRoom(myDate.getDate(), room);
            usersInRoom = bookingService.usersInRoom(myDate.getDate(), room);
            model.addAttribute("choosedate", myDate);
        }

        model.addAttribute("availablePlacesList", availablePlacesList);
        model.addAttribute("user", user);
        model.addAttribute("bookingList", bookingList);
        model.addAttribute("usersInRoom", usersInRoom);

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


        if (id == GYM) {
            return REDIRECT + "bookingtime/" + GYM;

        }
        if (id == CARDIO) {
            return REDIRECT + "bookingtime/" + CARDIO;
        }

        return REDIRECT + "bookingtime";

    }

    @GetMapping("/bookingconfirmation/{time}")
    public String bookingConfirmation(HttpSession session, Model model, @PathVariable String time) {

        User user = userService.getAuthenticatedUser();
        Booking booking = (Booking) session.getAttribute("booking");


        booking.setStart(LocalTime.parse(time));

        List<Booking> bookings = bookingService.findByDateAndStartAndRoom(booking.getDate(),
                booking.getStart(),
                booking.getRoom());


        if ((bookingService.isExists(booking.getDate(), booking.getStart(), booking.getRoom()))
                && (bookings.size() >= booking.getRoom().getCapacity())) {
            session.removeAttribute("booking");
            return REDIRECT;
        }

        if (bookingService.isAlreadyBookedAnyRoomAtSameTime(user, booking.getDate(), booking.getStart())) {
            return "errorDoubleReservation";
        }

        Long id = booking.getRoom().getId();

        bookingService.add(booking);
        session.removeAttribute("booking");

        model.addAttribute("user", user);
        model.addAttribute("booking", booking);

        if (id == GYM) {
            return REDIRECT + "bookingtime/" + GYM;
        }
        if (id == CARDIO) {
            return REDIRECT + "bookingtime/" + CARDIO;
        }
        return REDIRECT + "bookingtime/0";

    }

//    @PostMapping("bookingconfirmation/savebooking")
//    public String bookingConfirmation(HttpSession session) {
//
//        Booking booking = (Booking) session.getAttribute("booking");
//        Long userId = userService.getAuthenticatedUser().getId();
//
//        bookingService.add(booking);
//        session.removeAttribute("booking");
//
//        return REDIRECT + "panel/user/" + userId + "/bookings";
//    }

    @GetMapping("/panel/addextrahour/{id}")
    public String addNextHourToBookingByAdmin(Model model, @PathVariable(value = "id") Long id) {

        Booking booking = bookingService.getById(id);
        Long userId = booking.getUser().getId();

        log.debug("Add extra hour booking started");
        model.addAttribute("userId", userId);

        if (bookingService.isAlreadyBookedAnyRoomAtSameTime(booking.getUser(), booking.getDate(), booking.getStart().plusHours(1))) {
            return "errorAddNextHourReservation";
        } else {

            try {
                bookingService.addNextHourAsNewBooking(booking);
                log.info("Extra Hour Booking was added.");

                return REDIRECT + "panel/user/" + booking.getUser().getId() + "/bookings";


            } catch (Exception e) {
                log.error("Unable to add extra booking, message: " + e.getMessage(), e);
                return "errorPage";
            }
        }
    }
}