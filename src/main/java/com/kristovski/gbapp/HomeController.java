package com.kristovski.gbapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    private final String REDIRECT = "redirect:/";

    @RequestMapping("/")
    public String home() {
        return REDIRECT + "loginform";
    }

    @RequestMapping("/gym")
    @ResponseBody
    public String gym() {
        return "gym";
    }
}
