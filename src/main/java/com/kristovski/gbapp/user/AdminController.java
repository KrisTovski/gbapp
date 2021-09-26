package com.kristovski.gbapp.user;

import com.kristovski.gbapp.booking.Booking;
import com.kristovski.gbapp.booking.BookingService;
import com.kristovski.gbapp.security.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/panel")
public class AdminController {

    private UserServiceImpl userService;
    private BookingService bookingService;
    private IAuthenticationFacade authenticationFacade;

    public AdminController(UserServiceImpl userService,
                           BookingService bookingService,
                           IAuthenticationFacade authenticationFacade) {
        this.userService = userService;
        this.bookingService = bookingService;
        this.authenticationFacade = authenticationFacade;
    }

    @Autowired


    @GetMapping("/adminpanel")
    public String adminPanel() {
        return "panel/adminPanel";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        return getPaginatedUsers(1, "id", "asc", model);
    }

    @GetMapping("/updateUser/{id}")
    public String updateUser(@PathVariable(value = "id") long id, Model model) {
        User user = userService.getById(id);
        // pre-populate the form
        model.addAttribute("user", user);
        return "panel/updateUserForm";
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.mergeWithExistingAndUpdate(user);
        return "panel/updateSuccess";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable(value = "id") Long id) {
        userService.deleteById(id);


        return "redirect:/panel/users";
    }

    @GetMapping("/bookings")
    public String getAllBookings(Model model) {
        return getPaginatedBookings(1, "id", "asc", model);
    }

    @GetMapping("/user/{userId}/bookings")
    public String getAllBookingsByUser(@PathVariable(value = "userId") Long id, Model model) {
        User userById = userService.getById(id);
        String userLogin = userById.getLogin();
        model.addAttribute("userLogin", userLogin);

        Long currentUserId = getUser().getId();

        if (isAdmin()) {
            return getPaginatedBookingsByUser(id, 1, "id", "asc", model);
        }
        return getPaginatedBookingsByUser(currentUserId, 1, "id", "asc", model);
    }

    @GetMapping("/bookings/page/{pageNo}")
    private String getPaginatedBookings(@PathVariable(value = "pageNo") int pageNo,
                                        @RequestParam("sortField") String sortField,
                                        @RequestParam("sortDir") String sortDir,
                                        Model model) {
        int pageSize = 5;
        Page<Booking> page = bookingService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Booking> listBookings = page.getContent();
        addPaginationAttributes(pageNo, sortField, sortDir, model, page.getTotalPages(), page.getTotalElements());

        model.addAttribute("listBookings", listBookings);

        return "panel/bookingList";

    }

    @GetMapping("/page/{pageNo}")
    public String getPaginatedUsers(@PathVariable(value = "pageNo") int pageNo,
                                    @RequestParam("sortField") String sortField,
                                    @RequestParam("sortDir") String sortDir,
                                    Model model) {
        int pageSize = 5;
        Page<User> page = userService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<User> listUsers = page.getContent();
        addPaginationAttributes(pageNo, sortField, sortDir, model, page.getTotalPages(), page.getTotalElements());

        model.addAttribute("listUsers", listUsers);

        return "panel/usersList";

    }

    @GetMapping("user/{id}/bookings/page/{pageNo}")
    public String getPaginatedBookingsByUser(@PathVariable(value = "id") Long id,
                                             @PathVariable(value = "pageNo") int pageNo,
                                             @RequestParam("sortField") String sortField,
                                             @RequestParam("sortDir") String sortDir,
                                             Model model) {
        int pageSize = 10;
        Page<Booking> page = bookingService.findPaginatedByUser(id, pageNo, pageSize, sortField, sortDir);

        List<Booking> listBookings = page.getContent();
        addPaginationAttributes(pageNo, sortField, sortDir, model, page.getTotalPages(), page.getTotalElements());

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
