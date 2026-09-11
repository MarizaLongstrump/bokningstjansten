package com.mariza.bokning.repository;

import com.mariza.bokning.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//Ett Repository är länken mellan Entity och databasen.
//Det gör att man kan spara, hämta, uppdatera och ta bort hotell utan att skriva SQL kod.

    @Repository
        public interface HotelRepository extends JpaRepository<Hotel, Long> {
        Optional<Hotel> findById(long id);
        Optional<Hotel> findByHotelName(String hotelName);
        Optional<Hotel> deleteByHotelName(String hotelName);


    }
