package com.mariza.hotel.controller.web;

import com.mariza.hotel.entity.Room;
import org.springframework.ui.Model;
import com.mariza.hotel.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
    public class RoomWebController {

    private final RoomService roomService;

    public RoomWebController(RoomService roomService) {
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

    // skapar web controller for formulär
    @GetMapping("/rooms/create")
    public String showCreateRoomForm(Model model) {
        model.addAttribute("room", new Room());
        return "createRoom";
    }

    @PostMapping("/rooms/create")
    public String createRoom(Room room) {
        roomService.createRoom(room);
        return "redirect:/rooms";
    }







}
