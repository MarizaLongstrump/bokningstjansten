package com.mariza.hotel.controller;

import com.mariza.hotel.entity.Hotel;
import com.mariza.hotel.repository.HotelRepository;
import com.mariza.hotel.service.HotelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
// controller jobb är bara tar emot anrop returnera svar
// med vem controller pratar? med frontend??
@RestController
@RequestMapping("/hotel")
public class HotelController {

    private HotelService hotelService;

    public  HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    public List<Hotel> gedAllHotels() {
        return hotelService.findAllHotels();
    }

    @GetMapping("/{id}")
    public Hotel gedHotelById(@PathVariable Long id) {
        return hotelService.findHotelById(id);

    }

}
