package com.mariza.bokning.controller.api;

import com.mariza.bokning.dto.hotel.CreateHotelRequest;
import com.mariza.bokning.dto.hotel.HotelResponse;
import com.mariza.bokning.dto.hotel.UpdateHotelRequest;
import com.mariza.bokning.entity.Hotel;
import com.mariza.bokning.service.HotelService;
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
    @GetMapping("/{name}")
    public HotelResponse getHotel(@PathVariable String name) {
        return hotelService.findHotelByHotelName(name);
    }



    //En adress i din backend som frontend eller Postman kan skicka data till.
    //*** ADMIN NIVÅ
    @PostMapping
    public Hotel createHotelRequest(@RequestBody CreateHotelRequest createHotelRequest) {
        return hotelService.createHotel(createHotelRequest);

    }
    // ** ADMIN NIVÅ
    @DeleteMapping("/{name}")
    public void deleteHotelByHotelName(@PathVariable String name) {
        hotelService.deleteHotel(name);
    }
    // *** ADMIN NIVÅ
    @PutMapping("/{id}")
    public Hotel updateHotel(@PathVariable Long id, @RequestBody UpdateHotelRequest request) {
        return hotelService.updateHotel(id,request);
    }




}
