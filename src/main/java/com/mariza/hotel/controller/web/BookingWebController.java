package com.mariza.hotel.controller.web;

import com.mariza.hotel.controller.api.BookingController;
import com.mariza.hotel.dto.bookning.CreateBookingRequest;
import com.mariza.hotel.entity.Booking;
import com.mariza.hotel.service.BookingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // anoterar klass

    public class BookingWebController {

    private final BookingService bookingService;

    // injicera service via konstruktör

    public BookingWebController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/bookingForms") // ska göra en länk till den här adress för att köra den har koden
    public String showBookingForm(Model model) {
        model.addAttribute("booking", new CreateBookingRequest()); // en tom booking request
        return "bookingForms"; // skapa template med det här namnet
    }

    @PostMapping("/bookingForms")
    public String processBookingForm(@ModelAttribute CreateBookingRequest bookingRequest) {
        bookingService.createBooking(bookingRequest);
        return "redirect:/rooms";
    }




}
