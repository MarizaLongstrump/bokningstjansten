package com.mariza.hotel.service;


import com.mariza.hotel.dto.CreateHotelRequest;
import com.mariza.hotel.dto.UpdateHotelRequest;
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

    public Hotel createHotel (CreateHotelRequest createHotelRequest) {
        Hotel hotel = new Hotel();
        hotel.setHotelName(createHotelRequest.getName());
        hotel.setHotelAddress(createHotelRequest.getAdress());
        hotel.setHotelCity(createHotelRequest.getCity());
        hotel.setHotelRating(createHotelRequest.getStars()*1.0);
        return hotelRepository.save(hotel);
    }

    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }

    public Hotel updateHotel(Long id, UpdateHotelRequest request) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found- klass HotelService"));
                hotel.setHotelName(request.getName());
                hotel.setHotelAddress(request.getAddress());
                hotel.setHotelCity(request.getCity());
                hotel.setHotelRating(request.getStars()*1.0);
                return hotelRepository.save(hotel);


    }

    }
