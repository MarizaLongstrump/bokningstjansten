package com.mariza.bokning.repository;

import com.mariza.bokning.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Ett Repository är länken mellan Entity och databasen.
//Det gör att man kan spara, hämta, uppdatera och ta bort hotell utan att skriva SQL kod.

    @Repository
        public interface HotelRepository extends JpaRepository<Hotel, Long> {
    }
