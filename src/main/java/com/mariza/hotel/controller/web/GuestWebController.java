package com.mariza.hotel.controller.web;


import com.mariza.hotel.dto.guest.CreateGuestRequest;
import com.mariza.hotel.dto.guest.GuestResponse;
import com.mariza.hotel.dto.guest.UpdateGuestRequest;
import com.mariza.hotel.entity.Guest;
import com.mariza.hotel.service.GuestService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
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
        public String showRegisterForm(Model model, HttpSession session) {
            model.addAttribute("guest", new CreateGuestRequest());
            model.addAttribute("guestInloggade", session.getAttribute("name"));
            return "register";
        }
        /*
        @PostMapping("/register")
        public String registerGuest(@ModelAttribute CreateGuestRequest guestRequest) {
            guestService.createGuest(guestRequest);
            return "redirect:/";
        }*/
// @Valid behövs eftersom valideringsfel är inte exception
// det går inte till global exception handler
// valideringsfel hanteras direkt i webControle via bindingResults had erros
    @PostMapping("/register")
    public String registerGuest(@Valid @ModelAttribute("guest") CreateGuestRequest request,
                                BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) { // validera först
            return "register";
        }

        try {
            guestService.createGuest(request); // sen anropar service create metoden
            return "redirect:/account/create";
        } catch (IllegalArgumentException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "register"; // samma sida igen
        }

        }

    @GetMapping("/guest/update")
    public String updateGuestInformationForm (Model model, HttpSession session) {
        Long guestId = (Long) session.getAttribute("userId");
        Guest guest = guestService.findById(guestId);
        UpdateGuestRequest updateGuestRequest = new UpdateGuestRequest();
        updateGuestRequest.setGuestId(guestId);
        updateGuestRequest.setPrefix(guest.getPrefix());
        updateGuestRequest.setFirstName(guest.getFirstName());
        updateGuestRequest.setLastName(guest.getLastName());
        updateGuestRequest.setEmail(guest.getEmail());
        updateGuestRequest.setTelephone(guest.getTelephone());
        updateGuestRequest.setNationality(guest.getNationality());
        model.addAttribute("guest", updateGuestRequest);
        model.addAttribute("guestInloggade", session.getAttribute("name"));

        return "guest";
    }

    @PostMapping("/guest/update/{guestId}")
    public String updateGuestInformation (@PathVariable Long guestId,
                                          @ModelAttribute("guest") UpdateGuestRequest updateGuestRequest ){
        guestService.updateGuest(guestId, updateGuestRequest);
        return "redirect:/account/details";

    }



}

