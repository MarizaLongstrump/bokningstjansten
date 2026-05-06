package com.mariza.hotel.service;


import com.mariza.hotel.entity.Hotel;
import com.mariza.hotel.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
// innehåller logic
@Service
    public class HotelService {

    private HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public List<Hotel> findAllHotels() {
        return hotelRepository.findAll();
    }

    public Hotel findHotelById(Long id) {

          return hotelRepository.findById(id)
                  .orElseThrow(() -> new RuntimeException("Hotel not found- klass HotelService"));



    }

    }
