package com.mariza.hotel.repository;

import com.mariza.hotel.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
    public interface GuestRepository extends JpaRepository<Guest, Long> {
        // den här metoden finns inte men jag vill findByFirstName så jag
        // måste skapa det här först
        Guest findByFirstName(String firstName);
        Guest findByLastName(String lastName);
    }



