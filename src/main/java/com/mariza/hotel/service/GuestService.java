package com.mariza.hotel.service;

import com.mariza.hotel.dto.guest.CreateGuestRequest;
import com.mariza.hotel.dto.guest.GuestResponse;
import com.mariza.hotel.dto.guest.UpdateGuestRequest;
import com.mariza.hotel.entity.Guest;
import com.mariza.hotel.repository.GuestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
    public class GuestService {

        private GuestRepository guestRepository;
        //Konstruktorn säger till Spring:
        //När jag skapar GuestService, ge mig ett färdigt GuestRepository
        // objekt så jag kan använda det i mina metoder.”

        public GuestService(GuestRepository guestRepository) {
            this.guestRepository = guestRepository;
        }
        //*** det behovs fixa för att returnera RESPONSE
        // *** det är på ADMIN nivå
        /*
        public List<Guest> findAll() {
            return guestRepository.findAll();
        }*/



        // ****** använder till ANVÄNDAREN  delar

        public Guest findByFirstName(String firstName) {
            return guestRepository.findByFirstName(firstName)
                    .orElse(null);
        }

        public Guest findByLastName(String lastName) {
            return guestRepository.findByLastName(lastName)
                    .orElse(null);
        }

        // CreateGuestRequest kommer från DTO

        public Guest createGuest(CreateGuestRequest createGuestRequest) {
            Guest guest = new Guest();
            guest.setFirstName(createGuestRequest.getFirstName());
            guest.setLastName(createGuestRequest.getLastName());
            guest.setEmail(createGuestRequest.getEmail());
            guest.setPrefix(createGuestRequest.getPrefix());
            guest.setTelephone(createGuestRequest.getTelephone());
            guest.setNationality(createGuestRequest.getNationality());
            return guestRepository.save(guest);
        }

        public void deleteGuest(Long id) {
            guestRepository.deleteById(id);
        }


        public Guest updateGuest(Long id, UpdateGuestRequest updateGuestRequest) {
            Guest guest = guestRepository.findById(id)
                    .orElseThrow(()->new RuntimeException("Guest not found- GuestService klass"));
                     guest.setFirstName(updateGuestRequest.getFirstName());
                     guest.setLastName(updateGuestRequest.getLastName());
                     guest.setEmail(updateGuestRequest.getEmail());
                     guest.setPrefix(updateGuestRequest.getPrefix());
                     guest.setTelephone(updateGuestRequest.getTelephone());
                     guest.setNationality(updateGuestRequest.getNationality());
                     return guestRepository.save(guest);
        }
        //**** spara till admin delar
        public List<GuestResponse> findAllGuestByLastName(String lastName) {
            List<Guest> guestList = guestRepository.findAllGuestByLastName(lastName);
            return guestList.stream()
                    .map(guest ->{
            GuestResponse guestResponse = new GuestResponse();
            guestResponse.setFirstName(guest.getFirstName());
            guestResponse.setLastName(guest.getLastName());
            guestResponse.setEmail(guest.getEmail());
            guestResponse.setPrefix(guest.getPrefix());
            guestResponse.setTelephone(guest.getTelephone());
            return guestResponse;})
                    .toList();

        }






}
