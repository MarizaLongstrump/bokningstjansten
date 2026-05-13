package com.mariza.hotel.controller;

import com.mariza.hotel.dto.hotel.CreateHotelRequest;
import com.mariza.hotel.dto.hotel.HotelResponse;
import com.mariza.hotel.dto.hotel.UpdateHotelRequest;
import com.mariza.hotel.entity.Hotel;
import com.mariza.hotel.service.HotelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// controller jobb är bara tar emot anrop returnera svar
// med vem controller pratar? med frontend??
@RestController
@RequestMapping("/hotel")
public class HotelController {

    private final HotelService hotelService;

    public  HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }
    // *** ADMIN NIVÅ
    @GetMapping
    public List<Hotel> getAllHotels() {
        return hotelService.findAllHotels();
    }

    // fungerar som en fasade för säkerhetsskull
    @GetMapping("/{id}")
    public HotelResponse getHotel(@PathVariable Long id) {
        return hotelService.getHotelById(id);
    }



    //En adress i din backend som frontend eller Postman kan skicka data till.
    //*** ADMIN NIVÅ
    @PostMapping
    public Hotel createHotelRequest(@RequestBody CreateHotelRequest createHotelRequest) {
        return hotelService.createHotel(createHotelRequest);

    }
    // ** ADMIN NIVÅ
    @DeleteMapping("/{id}")
    public void deleteHotelById(@PathVariable Long id) {
        hotelService.deleteHotel(id);
    }
    // *** ADMIN NIVÅ
    @PutMapping("/{id}")
    public Hotel updateHotel(@PathVariable Long id, @RequestBody UpdateHotelRequest request) {
        return hotelService.updateHotel(id,request);
    }




}
