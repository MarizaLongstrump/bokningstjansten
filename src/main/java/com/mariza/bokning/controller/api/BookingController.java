package com.mariza.bokning.controller.api;

import com.mariza.bokning.dto.bookning.BookingResponse;
import com.mariza.bokning.dto.bookning.CreateBookingRequest;
import com.mariza.bokning.dto.bookning.UpdateBookingRequest;
import com.mariza.bokning.service.BookingService;
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

    @PostMapping
    public BookingResponse createBooking(@RequestBody CreateBookingRequest request) {
        return bookingService.createBooking(request);
    }

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

    @GetMapping("/customer/{customerId}")
    public List<BookingResponse> getBookingsByCustomerId(@PathVariable Long customerId) {
        return bookingService.getBookingsByCustomerId(customerId);
    }
}
