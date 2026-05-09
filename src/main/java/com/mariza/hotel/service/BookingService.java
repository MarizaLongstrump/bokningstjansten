package com.mariza.hotel.service;

import com.mariza.hotel.dto.bookning.BookingResponse;
import com.mariza.hotel.dto.bookning.CreateBookingRequest;
import com.mariza.hotel.entity.Booking;
import com.mariza.hotel.entity.Guest;
import com.mariza.hotel.entity.Hotel;
import com.mariza.hotel.entity.Room;
import com.mariza.hotel.repository.BookingRepository;
import com.mariza.hotel.repository.GuestRepository;
import com.mariza.hotel.repository.HotelRepository;
import com.mariza.hotel.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;

@Service
    public class BookingService {

        private GuestRepository guestRepository;
        private RoomRepository roomRepository;
        private HotelRepository hotelRepository;
        private BookingRepository bookingRepository;

        public BookingService(GuestRepository guestRepository, RoomRepository roomRepository, HotelRepository hotelRepository, BookingRepository bookingRepository) {
            this.guestRepository = guestRepository;
            this.roomRepository = roomRepository;
            this.hotelRepository = hotelRepository;
            this.bookingRepository = bookingRepository;
        }

        public BookingResponse mapToResponse(Booking booking) {
            BookingResponse bookingResponse = new BookingResponse(); // skapar en ny DTO objekt som skickas till postman
            bookingResponse.setBookningId(booking.getId());
            bookingResponse.setGuestFirstName(booking.getGuest().getFirstName());
            bookingResponse.setGuestLastName(booking.getGuest().getLastName());
            bookingResponse.setHotelName(booking.getHotel().getHotelName());
            bookingResponse.setRoomNumber(booking.getRoom().getRoomNumber());
            bookingResponse.setCheckInDate(booking.getCheckInDate());
            bookingResponse.setCheckOutDate(booking.getCheckOutDate());
            bookingResponse.setTotalNights(booking.getTotalNights());
            bookingResponse.setTotalNights(booking.getTotalNights());
            return bookingResponse;

        }

        // create booking har
        // booking entity, guest entity,room entity, hotel entity
        // och behövs ändra return typ till mapToResponse så återkommer.

        public BookingResponse createBooking(CreateBookingRequest createBookningRequest) {
           // Booking booking = new Booking();

            // 1- Hämta Guest, Room, Hotel

            Guest guest = guestRepository.findById(createBookningRequest.getGuestId())
                    .orElseThrow(()-> new RuntimeException("Guest not found"));



            Room room = roomRepository.findById(createBookningRequest.getRoomId())
                    .orElseThrow(()-> new RuntimeException("Room not found"));
            Hotel hotel = hotelRepository.findById(createBookningRequest.getHotelId())
                    .orElseThrow(()-> new RuntimeException("Hotel not found"));

            // 2- Räkna totalNights
            long nights = ChronoUnit.DAYS.between(
                    createBookningRequest.getCheckInDate(),
                    createBookningRequest.getCheckOutDate()
            );

            if (nights <= 0) {
                throw new RuntimeException("Check-out must be after check-in");
                // om det är negative blev det tvärtom
            }


            // 3- Räkna totalPrice
            double totalPrice = nights * room.getPricePerNight();

            // 4- Skapa Booking
            Booking booking = new Booking();
            booking.setGuest(guest);
            booking.setRoom(room);
            booking.setHotel(hotel);
            booking.setCheckInDate(createBookningRequest.getCheckInDate());
            booking.setCheckOutDate(createBookningRequest.getCheckOutDate());
            booking.setTotalNights((int) nights);
            booking.setTotalPrice(totalPrice);

            // 5- Spara Booking
            bookingRepository.save(booking);

            // 6. Mappa till BookingResponse
            BookingResponse bookingResponse = new BookingResponse();
            bookingResponse.setBookningId(booking.getId());
            bookingResponse.setGuestFirstName(guest.getFirstName());
            bookingResponse.setGuestLastName(guest.getLastName());
            bookingResponse.setHotelName(hotel.getHotelName());
            bookingResponse.setCheckInDate(booking.getCheckInDate());
            bookingResponse.setCheckOutDate(booking.getCheckOutDate());
            bookingResponse.setTotalNights(booking.getTotalNights());
            bookingResponse.setTotalPrice(totalPrice);
            return bookingResponse;

        }

        public BookingResponse getBookingById(long Id) {
            Booking booking = bookingRepository.findById(Id)
                    .orElseThrow(()-> new RuntimeException("Booking not found"));
            return mapToResponse(booking);
        }

}
