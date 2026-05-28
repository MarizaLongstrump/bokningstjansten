package com.mariza.hotel.controller.api;

import com.mariza.hotel.dto.bookning.BookingResponse;
import com.mariza.hotel.dto.bookning.CreateBookingRequest;
import com.mariza.hotel.dto.bookning.UpdateBookingRequest;
import com.mariza.hotel.entity.Guest;
import com.mariza.hotel.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    /* måste modificera för api användning create
    @PostMapping
    public BookingResponse createBooking(@RequestBody CreateBookingRequest request, Guest loggedInGuest) {
        return bookingService.createBooking(request,loggedInGuest);
    }*/
/*
    @GetMapping("/{email}")
    public List<BookingResponse> getBookingsByEmail(@PathVariable String email) {
        return bookingService.getBookingsByEmail(email);
    }*/

    @GetMapping("/{id}")
    public BookingResponse getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }

    @PutMapping("/{id}")
    public BookingResponse updateBooking(@PathVariable Long id,
                                         @RequestBody UpdateBookingRequest request) {
        return bookingService.updateBooking(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }

}
