package com.kristovski.gbapp.user;

import com.kristovski.gbapp.security.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


//    user can do:
//    - show his details,
//    - change name, password, email address
//    - reserve the room,
//    - cancel reservation,
//    - show all bookings,


@Controller
public class UserController {

    private UserServiceImpl userService;
    private IAuthenticationFacade authenticationFacade;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/panel/userpanel")
    public String userPanel(){
        return "panel/userPanel";
    }




}
