package com.mariza.bokning.service;


import com.mariza.bokning.dto.hotel.CreateHotelRequest;
import com.mariza.bokning.dto.hotel.HotelResponse;
import com.mariza.bokning.dto.hotel.UpdateHotelRequest;
import com.mariza.bokning.entity.Hotel;
import com.mariza.bokning.repository.HotelRepository;
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
        //1- skapar en ny hotel objekt
        Hotel hotel = new Hotel();
        // 2- kopierar värde från hotelRequest
        hotel.setHotelName(createHotelRequest.getName());
        hotel.setHotelAddress(createHotelRequest.getAdress());
        hotel.setHotelCity(createHotelRequest.getCity());
        // anropar hotelRepository och sparar och returnerar resultat
        return hotelRepository.save(hotel);
    }

    public void deleteHotel(String hotelName) {
        hotelRepository.deleteByHotelName(hotelName);
    }

    public Hotel updateHotel(Long id, UpdateHotelRequest request) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found- klass HotelService"));
                hotel.setHotelName(request.getName());
                hotel.setHotelAddress(request.getAddress());
                hotel.setHotelCity(request.getCity());

                return hotelRepository.save(hotel);
    }

    public HotelResponse findHotelByHotelName(String name) {
        Hotel hotel = hotelRepository.findByHotelName(name)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));
        HotelResponse hotelResponse = new HotelResponse();
        hotelResponse.setId(hotel.getId());
        hotelResponse.setName(hotel.getHotelName());
        hotelResponse.setCity(hotel.getHotelCity());

        return hotelResponse;
    }


}
