package com.mariza.hotel.service;

import com.mariza.hotel.dto.CreateGuestRequest;
import com.mariza.hotel.dto.UpdateGuestRequest;
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

        public List<Guest> findAll() {
            return guestRepository.findAll();
        }
        // för att göra det måste jag skapa metod i GuestRepository
        public Guest findByFirstName(String firstName) {
            return guestRepository.findByFirstName(firstName);
        }
        // för att göra det måste jag skapa metod i GuestRepository
        public Guest findByLastName(String lastName) {
            return guestRepository.findByLastName(lastName);
        }

        // CreateGuestRequest kommer från DTO

        public Guest createGuest(CreateGuestRequest createGuestRequest) {
            Guest guest = new Guest();
            guest.setFirstName(guest.getFirstName());
            guest.setLastName(guest.getLastName());
            guest.setEmail(guest.getEmail());
            guest.setPrefix(guest.getPrefix());
            guest.setTelephone(guest.getTelephone());
            guest.setNationality(guest.getNationality());
            return guestRepository.save(guest);
        }

        public void deleteGuest(Guest guest) {
            guestRepository.delete(guest);
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





}
