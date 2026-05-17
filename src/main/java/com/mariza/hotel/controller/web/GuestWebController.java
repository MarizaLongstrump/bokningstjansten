package com.mariza.hotel.controller.web;


import com.mariza.hotel.dto.guest.CreateGuestRequest;
import com.mariza.hotel.entity.Guest;
import com.mariza.hotel.service.GuestService;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GuestWebController {

        private final GuestService guestService;

        public GuestWebController(GuestService guestService) {
            this.guestService = guestService;
        }

        @GetMapping("/register")
        public String showRegisterForm(Model model) {
            model.addAttribute("guest", new CreateGuestRequest());
            return "register";
        }

        @PostMapping("/register")
        public String registerGuest(@ModelAttribute CreateGuestRequest guestRequest) {
            guestService.createGuest(guestRequest);
            return "redirect:/";
        }
    }

