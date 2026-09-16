package com.mariza.bokning.controller.web;

import com.mariza.bokning.dto.room.RoomResponse;
import com.mariza.bokning.service.BookingService;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import com.mariza.bokning.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Controller
public class RoomWebController {

    private final RoomService roomService;
    private final BookingService bookingService;

    public RoomWebController(RoomService roomService, BookingService bookingService) {
        this.roomService = roomService;
        this.bookingService = bookingService;
    }


    @GetMapping("/rooms")
    public String showAllRooms(Model model, HttpSession session) {
        List<RoomResponse> list = roomService.getAllRooms();
        System.out.println("CONTROLLER DEBUG -> type = " + list.get(0).getClass().getName());
        model.addAttribute("rooms", list);
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://customer-service:8081/api/customers/" + session.getAttribute("customerId");
//        CustomerResponse customerResponse= restTemplate.getForObject(url, CustomerResponse.class);
//        model.addAttribute("guestInloggade", customerResponse.getFirstName());
        return "rooms";
    }


    // 2. Visa detaljer för ett rum
    @GetMapping("/rooms/{roomNumber}")
    public String showRoomDetails(@PathVariable int roomNumber, Model model) {
        model.addAttribute("room", roomService.getRoomByRoomNumber(roomNumber));
        return "roomDetails";
    }


}
