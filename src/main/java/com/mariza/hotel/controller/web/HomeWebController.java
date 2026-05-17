package com.mariza.hotel.controller.web;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeWebController {
/*
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "MarizaGrand Hotel.");
        return "home";
    }
*/
    @Controller
    public class HomeController {

        @GetMapping("/")
        public String home() {
            return "home";
        }
    }

}


