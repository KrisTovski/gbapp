package com.kristovski.gbapp.user;

import com.kristovski.gbapp.booking.Booking;
import com.kristovski.gbapp.booking.BookingService;
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

    private static final int PAGE_SIZE = 10;
    private String REDIRECT = "redirect:/";

    private UserServiceImpl userService;
    private BookingService bookingService;

    @Autowired
    public UserController(UserServiceImpl userService, BookingService bookingService) {
        this.userService = userService;
        this.bookingService = bookingService;
    }

    @GetMapping("/user-details")
    public String getDetails(Model model) {
        User user = userService.getAuthenticatedUser();
        model.addAttribute("user", user);
        return "userDetails";
    }

    @GetMapping("/update-name")
    public String updateName(Model model) {
        User user = userService.getAuthenticatedUser();
        // pre-populate the form
        model.addAttribute("user", user);

        return "updateUserName";
    }

    @PostMapping("/update-name")
    public String updateName(@ModelAttribute("user") User user, Model model) {
        userService.updateName(user);
        model.addAttribute("user", user);
        return "updateUserDetailsSuccess";
    }

    @GetMapping("/delete-user-confirmation")
    public String disableUser() {
        return "DeleteConfirmation";
    }

    @GetMapping("/user-delete")
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

    @GetMapping("/user-bookings")
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
}
