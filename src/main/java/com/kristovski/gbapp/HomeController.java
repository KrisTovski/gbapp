package com.kristovski.gbapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    private final String REDIRECT = "redirect:/";

    @GetMapping("/")
    public String home() {
        return REDIRECT + "loginform";
    }

    @GetMapping("/gym")
    public String gym() {
        return "gym";
    }
}
