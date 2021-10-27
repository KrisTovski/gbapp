package com.kristovski.gbapp.user;

import com.kristovski.gbapp.booking.Booking;
import com.kristovski.gbapp.booking.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

//    user can do:
//    - show his details
//    - change name
//    - change password
//    - delete account (disable account - only Admin can delete permanently)
//    - reserve the room
//    - show all bookings
//    - cancel reservation

@Controller
public class UserController {

    Logger log = LoggerFactory.getLogger(this.getClass());

    private static final int PAGE_SIZE = 10;
    private String REDIRECT = "redirect:/";

    private UserServiceImpl userService;
    private BookingService bookingService;

    @Autowired
    public UserController(UserServiceImpl userService, BookingService bookingService) {
        this.userService = userService;
        this.bookingService = bookingService;
    }

    @GetMapping("/user/details")
    public String getDetails(Model model) {
        User user = userService.getAuthenticatedUser();
        model.addAttribute("user", user);
        return "userDetails";
    }

    @GetMapping("/update/name")
    public String updateName(Model model) {
        User user = userService.getAuthenticatedUser();
        // pre-populate the form
        model.addAttribute("user", user);

        return "updateUserName";
    }

    @PostMapping("/update/name")
    public String updateName(@ModelAttribute("user") User user, Model model) {
        userService.updateName(user);
        model.addAttribute("user", user);
        return "updateUserDetailsSuccess";
    }

    @GetMapping("/delete/user/confirmation")
    public String disableUser() {
        return "deleteAccountConfirmation";
    }

    @GetMapping("/user/delete")
    public String disableUserConfirmation(HttpServletRequest request) {
        User authenticatedUser = userService.getAuthenticatedUser();
        userService.disable(authenticatedUser);
        try {
            request.logout();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        return REDIRECT;
    }

    @GetMapping("/user/bookings")
    public String getAllBookings(Model model) {
        User authenticatedUser = userService.getAuthenticatedUser();
        model.addAttribute("userLogin", authenticatedUser.getLogin());

        Long id = userService.getAuthenticatedUser().getId();

        return getPaginatedBookingsByUser(id, 1, "id", "asc", model);
    }

    @GetMapping("/user/{id}/bookings/page/{pageNo}")
    public String getPaginatedBookingsByUser(@PathVariable(value = "id") Long id,
                                             @PathVariable(value = "pageNo") int pageNo,
                                             @RequestParam("sortField") String sortField,
                                             @RequestParam("sortDir") String sortDir,
                                             Model model) {
        int pageSize = PAGE_SIZE;

        Page<Booking> page = bookingService.findPaginatedByUser(id, pageNo, pageSize, sortField, sortDir);

        List<Booking> listBookings = page.getContent();
        addPaginationAttributes(pageNo, sortField, sortDir, model, page.getTotalPages(), page.getTotalElements());

        User authenticatedUser = userService.getAuthenticatedUser();
        model.addAttribute("userLogin", authenticatedUser.getLogin());
        model.addAttribute("id", id);
        model.addAttribute("listBookings", listBookings);

        return "panel/userBookingList";

    }

    private void addPaginationAttributes(@PathVariable("pageNo") int pageNo,
                                         @RequestParam("sortField") String sortField,
                                         @RequestParam("sortDir") String sortDir,
                                         Model model, int totalPages, long totalElements) {

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalElements);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
    }

    @GetMapping("/delete/booking/{id}/confirmation")
    public String deleteBookingConfirmation(@PathVariable(value = "id") Long id, Model model) {
        Booking booking = bookingService.getById(id);
        model.addAttribute("booking", booking);
        return "deleteBookingConfirmation";
    }


    @GetMapping("/delete/booking/{id}")
    public String deleteBooking(@PathVariable(value = "id") Long id) {

        try {
            bookingService.deleteById(id);
            log.info("Booking with ID " + id + " was deleted.");
            return REDIRECT + "user/bookings";

        } catch (Exception e) {
            log.error("Unable to delete booking with ID: " + id + ", message: " + e.getMessage(), e);
            return "errorPage";
        }
    }

    @GetMapping("/addextrahour/{id}")
    public String addNextHourToBooking(Model model, @PathVariable(value = "id") Long id) {

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

                return REDIRECT + "user/bookings";

            } catch (Exception e) {
                log.error("Unable to add extra booking, message: " + e.getMessage(), e);
                return "errorPage";
            }
        }
    }
}
