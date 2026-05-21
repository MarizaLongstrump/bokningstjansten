package com.mariza.hotel.controller.web;

import com.mariza.hotel.controller.api.BookingController;
import com.mariza.hotel.dto.bookning.BookingResponse;
import com.mariza.hotel.dto.bookning.CreateBookingRequest;
import com.mariza.hotel.dto.bookning.UpdateBookingRequest;
import com.mariza.hotel.entity.Booking;
import com.mariza.hotel.service.BookingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/booking/update/{bookingId}")
    public String updateBookingForm(@PathVariable Long bookingId, Model model) {
        BookingResponse bookingResponse = bookingService.getBookingById(bookingId);
        UpdateBookingRequest updateBookingRequest = new UpdateBookingRequest();
        updateBookingRequest.setBookingId(bookingResponse.getBookingId());
        updateBookingRequest.setGuestId(bookingResponse.getGuestId());
        updateBookingRequest.setHotelId(bookingResponse.getHotelId());
        updateBookingRequest.setRoomNumber(bookingResponse.getRoomNumber());
        updateBookingRequest.setCheckInDate(bookingResponse.getCheckInDate());
        updateBookingRequest.setCheckOutDate(bookingResponse.getCheckOutDate());

        model.addAttribute("booking",updateBookingRequest);
        return "updateBooking"; // var jag ska navigera till

    }

    @PostMapping("/booking/update/{bookingId}")
    public String updateBooking(@PathVariable Long bookingId,
                                @ModelAttribute("booking") UpdateBookingRequest request) {
        bookingService.updateBooking(bookingId, request);

        return "redirect:/bookings";
    }


    @GetMapping("/bookings")
    public String showBookingPage(Model model) {
        model.addAttribute("bookings",bookingService.getAllBookings());
        return "bookings";
    }

    @PostMapping("/booking/delete/{id}")
    public String deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return "redirect:/bookings"; // eller var visas listan
    }





}
