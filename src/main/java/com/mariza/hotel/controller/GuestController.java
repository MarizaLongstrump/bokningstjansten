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
        /*   Till ADMIN DEL
        @GetMapping
        public List<Guest> getAllGuests() {
            return guestService.findAll();
        }


        // spara till ADMIN del
        @GetMapping("/{lastName}")
        public List<GuestResponse> getGuestByLastName(@PathVariable String lastName) {
        return guestService.findAllGuestByLastName(lastName);

        }

        */

        @GetMapping("/{lastName}")
        public Guest getGuestsByLastName(@PathVariable String lastName) {
            return guestService.findByLastName(lastName);
        }

        @GetMapping("/{firstName}")
        public Guest getGuestsByFirstName(@PathVariable String firstName) {
            return guestService.findByFirstName(firstName);
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
