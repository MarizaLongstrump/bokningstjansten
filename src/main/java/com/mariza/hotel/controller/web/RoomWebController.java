package com.mariza.hotel.controller.web;

import com.mariza.hotel.dto.Room.RoomResponse;
import com.mariza.hotel.dto.bookning.BookingResponse;
import com.mariza.hotel.entity.Booking;
import com.mariza.hotel.entity.Room;
import com.mariza.hotel.service.BookingService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import com.mariza.hotel.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;


@Controller
public class RoomWebController {

    private final RoomService roomService;
    private final BookingService bookingService;

    public RoomWebController(RoomService roomService, BookingService bookingService) {
        this.roomService = roomService;
        this.bookingService = bookingService;
    }

    // 1. Visa alla rum (RoomResponse)
    /*
    @GetMapping("/rooms")
    public String showAllRooms(Model model) {
        model.addAttribute("rooms", roomService.getAllRooms());
        return "rooms";
    }*/

    @GetMapping("/rooms")
    public String showAllRooms(Model model, HttpSession session) {
        List<RoomResponse> list = roomService.getAllRooms();
        System.out.println("CONTROLLER DEBUG -> type = " + list.get(0).getClass().getName());
        model.addAttribute("rooms", list);
        model.addAttribute("guestInloggade", session.getAttribute("name"));
        return "rooms";
    }


    // 2. Visa detaljer för ett rum
    @GetMapping("/rooms/{roomNumber}")
    public String showRoomDetails(@PathVariable int roomNumber, Model model) {
        model.addAttribute("room", roomService.getRoomByRoomNumber(roomNumber));
        return "roomDetails";
    }


}
