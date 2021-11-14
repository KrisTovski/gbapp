package com.kristovski.gbapp.api.controller;


import com.kristovski.gbapp.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RegistrationRestController {

    private UserServiceImpl userService;

    @Autowired
    public RegistrationRestController(UserServiceImpl userService) {
        this.userService = userService;
    }




}
