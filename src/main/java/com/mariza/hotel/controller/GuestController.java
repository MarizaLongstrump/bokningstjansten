package com.mariza.hotel.controller;

import com.mariza.hotel.dto.guest.CreateGuestRequest;
import com.mariza.hotel.dto.guest.GuestResponse;
import com.mariza.hotel.dto.guest.UpdateGuestRequest;
import com.mariza.hotel.entity.Guest;
import com.mariza.hotel.service.GuestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guest")
    public class GuestController {

        private final GuestService guestService;

        public GuestController(GuestService guestService) {
            this.guestService = guestService;
        }

        @GetMapping
        public List<Guest> getAllGuests() {
            return guestService.findAll();
        }

        @GetMapping("/{lastName}")
        public GuestResponse getGuestByLastName(@PathVariable String lastName) {
            return guestService.getGuestByLastName(lastName);

        }


        @PostMapping
        public Guest createGuest(@RequestBody CreateGuestRequest createGuestRequest) {
            return guestService.createGuest(createGuestRequest);
        }

        @DeleteMapping("/{id}")
        public void deleteGuest(@PathVariable Long id) {
            guestService.deleteGuest(id);
        }

        @PutMapping("/{id}")
        public Guest updateGuest(@PathVariable Long id, @RequestBody UpdateGuestRequest updateGuestRequest) {
            return guestService.updateGuest(id, updateGuestRequest);
        }


    }
