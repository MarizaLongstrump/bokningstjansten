package com.mariza.hotel.repository;

import com.mariza.hotel.entity.Booking;
import com.mariza.hotel.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
    public interface GuestRepository extends JpaRepository<Guest, Long> {
        // den här metoden finns inte men jag vill findByFirstName så jag
        // måste skapa det här först
        Optional<Guest> findByFirstName(String firstName);
        Optional<Guest> findByLastName(String lastName);
        Optional<Guest> findByEmail(String email);
        List<Guest> findAllGuestByLastName(String lastName);
    }



