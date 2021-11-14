package com.kristovski.gbapp.user;

import com.kristovski.gbapp.booking.Booking;
import com.kristovski.gbapp.booking.BookingService;
import com.kristovski.gbapp.security.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/panel")
public class AdminController {

    private static final int PAGE_SIZE = 10;
    private UserServiceImpl userService;
    private BookingService bookingService;
    private IAuthenticationFacade authenticationFacade;

    @Autowired
    public AdminController(UserServiceImpl userService,
                           BookingService bookingService,
                           IAuthenticationFacade authenticationFacade) {
        this.userService = userService;
        this.bookingService = bookingService;
        this.authenticationFacade = authenticationFacade;
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        return getPaginatedUsers(1, "id", "asc", model);
    }

    @GetMapping("/update/user/{id}")
    public String updateUserById(@PathVariable(value = "id") long id, Model model) {
        User user = userService.getById(id);
        // pre-populate the form
        model.addAttribute("user", user);
        return "panel/updateUserForm";
    }

    @PostMapping("/update/user")
    public String updateUserById(@ModelAttribute("user") User user) {
        userService.mergeWithExistingAndUpdate(user);
        return "panel/updateSuccess";
    }

    @GetMapping("/delete/user/{id}/confirmation")
    public String disableUser(@PathVariable(value = "id") Long id, Model model) {
        User user = userService.getById(id);
        String login = user.getLogin();
        model.addAttribute("id", id);
        model.addAttribute("login", login);
        model.addAttribute("user", user);
        return "panel/deleteUserConfirmation";
    }

    @GetMapping("/delete/user/{id}")
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

        Long currentUserId = userService.getAuthenticatedUser().getId();


        if (userService.isAdmin()) {
            return getPaginatedBookingsByUser(id, 1, "id", "asc", model);
        }
        return getPaginatedBookingsByUser(currentUserId, 1, "id", "asc", model);
    }

    @GetMapping("/user/{userId}")
    public String getUserById(@PathVariable(value = "userId") Long id, Model model) {
        if (userService.isAdmin()) {
            User userById = userService.getById(id);
            extractedUser(id, model, userById);
        } else {
            User authenticatedUser = userService.getAuthenticatedUser();
            extractedUser(authenticatedUser.getId(), model, authenticatedUser);
        }
        return "panel/userInfo";
    }

    private void extractedUser(@PathVariable("userId") Long id, Model model, User userById) {
        String userLogin = userById.getLogin();
        model.addAttribute("user", userById);
        model.addAttribute("userid", id);
        model.addAttribute("userFirstName", userById.getFirstName());
        model.addAttribute("userLogin", userLogin);
    }


    @GetMapping("/bookings/page/{pageNo}")
    private String getPaginatedBookings(@PathVariable(value = "pageNo") int pageNo,
                                        @RequestParam("sortField") String sortField,
                                        @RequestParam("sortDir") String sortDir,
                                        Model model) {
        int pageSize = PAGE_SIZE;

        Page<Booking> page = bookingService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Booking> listBookings = page.getContent();
        addPaginationAttributes(pageNo, sortField, sortDir, model, page.getTotalPages(), page.getTotalElements());

        model.addAttribute("listBookings", listBookings);

        return "panel/bookingList";

    }

    @GetMapping("/users/page/{pageNo}")
    public String getPaginatedUsers(@PathVariable(value = "pageNo") int pageNo,
                                    @RequestParam("sortField") String sortField,
                                    @RequestParam("sortDir") String sortDir,
                                    Model model) {
        int pageSize = PAGE_SIZE;
        Page<User> page = userService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<User> listUsers = page.getContent();
        addPaginationAttributes(pageNo, sortField, sortDir, model, page.getTotalPages(), page.getTotalElements());

        model.addAttribute("listUsers", listUsers);

        return "panel/usersList";

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
