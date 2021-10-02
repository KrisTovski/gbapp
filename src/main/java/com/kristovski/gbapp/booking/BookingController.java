package com.kristovski.gbapp.booking;

import com.kristovski.gbapp.Room.Room;
import com.kristovski.gbapp.Room.RoomService;
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
        return "/panel/bookings";
    }

    @GetMapping("/panel/updateBooking/{id}")
    public String update(@PathVariable(value = "id") long id, Model model) {
        Booking booking = bookingService.getById(id);
        // pre-populate the form
        model.addAttribute("booking", booking);
        return "/panel/updateBookingForm";
    }

    @PostMapping("/panel/updateBooking")
    public String update(@ModelAttribute("booking") Booking booking) {
        bookingService.mergeWithExistingAndUpdate(booking);
        return "/panel/updateSuccess";
    }


    @GetMapping("/panel/deleteBooking/{id}")
    public String delete(@PathVariable(value = "id") Long id) {

        log.debug("Delete booking by Id started");
        User user = getUser();
        Long userId = user.getId();


        try {
            bookingService.deleteById(id);
            log.info("Booking with ID " + id + " was deleted.");
            if (isAdmin()) {
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
            Room roomById = roomService.findById(roomId);
            model.addAttribute("roomName", roomById.getName());
            booking.setRoom(roomById);
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
            bookingList = bookingService.findByDate(now, roomService.findById(roomId));
            availablePlacesList = bookingService.availablePlacesInRoom(now, roomService.findById(roomId));
            usersInRoom = bookingService.usersInRoom(now, roomService.findById(roomId));
            model.addAttribute("choosedate", new MyDate(now));
        } else {
            booking.setDate(myDate.getDate());
            bookingList = bookingService.findByDate(myDate.getDate(), roomService.findById(roomId));
            availablePlacesList = bookingService.availablePlacesInRoom(myDate.getDate(), roomService.findById(roomId));
            usersInRoom = bookingService.usersInRoom(myDate.getDate(), roomService.findById(roomId));
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

        List<Booking> bookings = bookingService.findByDateAndStartAndRoom(booking.getDate(),
                booking.getStart(),
                booking.getRoom());

        if ((bookingService.isExists(booking.getDate(), booking.getStart(), booking.getRoom()))
                && (bookings.size() >= booking.getRoom().getCapacity())) {
            session.removeAttribute("booking");
            return REDIRECT;
        }
        if (bookingService.alreadyBookedByUser(booking.getDate(), booking.getStart(), booking.getRoom(), booking.getUser().getId(), user.getId())) {
            session.removeAttribute("booking");
            return "errorDoubleReservation";
        }

        model.addAttribute("user", user);
        model.addAttribute("booking", booking);
        return "bookingconfirmation";
    }

    @PostMapping("bookingconfirmation/savebooking")
    public String bookingConfirmation(HttpSession session) {

        Booking booking = (Booking) session.getAttribute("booking");
        Long userId = getUser().getId();

        bookingService.add(booking);
        session.removeAttribute("booking");

        return REDIRECT + "panel/user/" + userId + "/bookings";
    }

    @GetMapping("/panel/addextrahour/{id}")
    public String addNextHourToBooking(Model model, @PathVariable(value = "id") Long id) {

        Booking booking = bookingService.getById(id);

        log.debug("Add extra hour booking started");
        User user = getUser();
        Long userId = user.getId();
        model.addAttribute("userId", userId);

        if (bookingService.alreadyBookedByUser(booking.getDate(), booking.getStart().plusHours(1), booking.getRoom(), booking.getUser().getId(), user.getId())) {
            return "errorAddNextHourReservation";
        } else {

            try {
                bookingService.addNextHourAsNewBooking(booking);
                log.info("Extra Hour Booking was added.");
                if (isAdmin()) {
                    return REDIRECT + "panel/user/" + booking.getUser().getId() + "/bookings";
                }
                return REDIRECT + "panel/user/" + userId + "/bookings";

            } catch (Exception e) {
                log.error("Unable to add extra booking, message: " + e.getMessage(), e);
                return "errorPage";
            }
        }

    }

    private User getUser() {
        Authentication loggedInUser = authenticationFacade.getAuthentication();
        String userEmail = loggedInUser.getName();
        User user = userService.findByEmail(userEmail);
        return user;
    }

    private boolean isAdmin() {
        Authentication loggedInUser = authenticationFacade.getAuthentication();
        if (loggedInUser != null && loggedInUser.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return true;
        }
        return false;
    }


}
