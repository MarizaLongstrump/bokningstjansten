package com.mariza.hotel.controller;

import org.springframework.ui.Model;
import com.mariza.hotel.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
    public class RumWebController {

    private final RoomService roomService;

    public RumWebController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping ("/rooms")
    public String showAllRooms(Model model) {
        model.addAttribute("rooms",roomService.findAllRooms());
        return "rooms";
    }
    @GetMapping("/rooms/{roomNumber}")
    public String showRoomDetails(@PathVariable int roomNumber, Model model) {
        model.addAttribute("room", roomService.getRoomByRoomNumber(roomNumber));
        return "roomDetails";
    }


}
