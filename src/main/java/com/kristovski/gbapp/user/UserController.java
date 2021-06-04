package com.kristovski.gbapp.user;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class UserController {

    private UserServiceImpl userService;

    @GetMapping("/loginform")
    public String loginForm() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "loginForm";
        }
        return "loginSuccess";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "registerForm";
    }

    @PostMapping("/register")
    public String addUser(@ModelAttribute @Valid User user,
                          BindingResult bindResult) {
        if (bindResult.hasErrors())
            return "registerForm";
        else {
            userService.addUserWithDefaultRole(user);
            return "registerSuccess";
        }
    }
    @GetMapping("/panel/users")
    public String getAll(Model model){
        model.addAttribute("listUsers", userService.findAll());
        return "panel/userslist";
    }

    @GetMapping("/panel/updateUser/{id}")
    public String updateUser(@PathVariable (value = "id") long id, Model model){
        User user = userService.getUserById(id);
        // pre-populate the form
        model.addAttribute("user", user);
        return "panel/updateUserForm";
    }


}
