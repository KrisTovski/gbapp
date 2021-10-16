package com.kristovski.gbapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

//    user can do:
//    - show his details
//    - change name, password, email address
//    - reserve the room
//    - show all bookings
//    - cancel reservation

@Controller
public class UserController {

    private UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("user-details")
    public String getDetails(Model model){
        User user = userService.getAuthenticatedUser();
        model.addAttribute("user", user);
        return "userDetails";
    }

    @GetMapping("/update-user-details")
    public String updateUser(Model model) {
        User user = userService.getAuthenticatedUser();
        // pre-populate the form
        model.addAttribute("user", user);
        return "updateUserDetails";
    }

    @PostMapping("/update-user-details")
    public String updateUser(@ModelAttribute("user") User user, Model model) {
        userService.mergeWithExistingAndUpdate(user);
        model.addAttribute("user", user);
        return "updateUserDetailsSuccess";
    }




}
