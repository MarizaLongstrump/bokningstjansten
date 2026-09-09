package com.mariza.bokning.controller.web;

import com.mariza.bokning.dto.Customer.CustomerResponse;
import com.mariza.bokning.dto.bookning.CreateBookingRequest;
import com.mariza.bokning.dto.bookning.UpdateBookingRequest;
import com.mariza.bokning.service.BookingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.client.RestTemplate;

@Controller
public class BookingWebController {

    private final BookingService bookingService;

    public BookingWebController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // 1. Visa bokningsformulär
    @GetMapping("/bookingForms")
    public String showBookingForm(@RequestParam int roomNumber, Model model) {
        CreateBookingRequest request = new CreateBookingRequest();
        request.setRoomNumber(roomNumber);
        model.addAttribute("booking", request);
        return "bookingForms";
    }

    // 2. Skapa bokning (customerId kommer från sessionen)
    @PostMapping("/bookingForms")
    public String createBooking(@ModelAttribute CreateBookingRequest bookingRequest,
                                HttpSession session,
                                Model model) {

        // Hämta customerId från sessionen
        Long customerId = (Long) session.getAttribute("customerId");
        if (customerId == null) {
            return "redirect:/account/login";
        }

        bookingRequest.setCustomerId(customerId);

        try {
            bookingService.createBooking(bookingRequest);
            return "redirect:/bookings";
        } catch (IllegalArgumentException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "bookingForms";
        }
    }

    // 3. Visa alla bokningar för inloggad kund
    @GetMapping("/bookings")
    public String showBookings(Model model, HttpSession session) {
        Long customerId = (Long) session.getAttribute("customerId");
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://customer-service:8081/api/customers/" + customerId;
        CustomerResponse customerResponse= restTemplate.getForObject(url, CustomerResponse.class);
        model.addAttribute("guestInloggade", customerResponse.getFirstName());

        if (customerId == null) {
            return "redirect:/account/login";
        }

        model.addAttribute("bookings", bookingService.getBookingsByCustomerId(customerId));
        return "bookings";
    }

    // 4. Visa update-formulär
    @GetMapping("/booking/update/{bookingId}")
    public String updateBookingForm(@PathVariable Long bookingId, Model model) {
        model.addAttribute("booking", bookingService.getBookingById(bookingId));
        return "updateBooking";
    }

    // 5. Uppdatera bokning
    @PostMapping("/booking/update/{bookingId}")
    public String updateBooking(@PathVariable Long bookingId,
                                @ModelAttribute("booking") UpdateBookingRequest request) {
        bookingService.updateBooking(bookingId, request);
        return "redirect:/bookings";
    }

    // 6. Ta bort bokning
    @PostMapping("/booking/delete/{id}")
    public String deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return "redirect:/bookings";
    }
}
