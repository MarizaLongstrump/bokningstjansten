package com.mariza.hotel.service;

import com.mariza.hotel.dto.guest.CreateGuestRequest;
import com.mariza.hotel.dto.guest.GuestResponse;
import com.mariza.hotel.dto.guest.UpdateGuestRequest;
import com.mariza.hotel.dto.hotel.CreateHotelRequest;
import com.mariza.hotel.dto.hotel.HotelResponse;
import com.mariza.hotel.entity.Guest;
import com.mariza.hotel.entity.Hotel;
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
        // det behovs fixa
        /*
        public List<Guest> findAll() {
            return guestRepository.findAll();
        }*/

    // ***find by e mail-> returnerar  användare ska använda det själv
    // som användare det är okay att returnera guest

        // för att göra det måste jag skapa metod i GuestRepository

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

        public GuestResponse getGuestByLastName(String lastName) {
            Guest guest = guestRepository.findByLastName(lastName)
                    .orElseThrow(()->new RuntimeException("Guest not found- GuestService klass"));
            GuestResponse guestResponse = new GuestResponse();
            guestResponse.setFirstName(guest.getFirstName());
            guestResponse.setLastName(guest.getLastName());
            guestResponse.setEmail(guest.getEmail());
            guestResponse.setPrefix(guest.getPrefix());
            guestResponse.setTelephone(guest.getTelephone());
            return guestResponse;

        }






}
