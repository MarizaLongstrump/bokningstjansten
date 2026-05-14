package com.mariza.hotel.service;

import com.mariza.hotel.dto.bookning.BookingResponse;
import com.mariza.hotel.dto.bookning.CreateBookingRequest;
import com.mariza.hotel.dto.bookning.UpdateBookingRequest;
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
import java.util.List;

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
                    .orElseThrow(() -> new RuntimeException("Guest not found"));

            Room room = roomRepository.findById(createBookningRequest.getRoomId())
                    .orElseThrow(() -> new RuntimeException("Room not found"));

            if (createBookningRequest.isExtraBed()) {
                if (room.getRoomType() != RoomType.doubleRoom) {
                    throw new RuntimeException("Extrasäng är endast tillåtet i dubbelrum");
                }

                if (!room.isExtraBedAvailable()) {
                    throw new RuntimeException("Detta dubbelrum har ingen extrasäng tillgänglig");
                }
            }


            Hotel hotel = hotelRepository.findById(createBookningRequest.getHotelId())
                    .orElseThrow(() -> new RuntimeException("Hotel not found"));

            // 2. Räkna totalNights
            // det här innehåller interessant funktion ChronoUnits.Days
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
            booking.setTotalNights((int) nights); //ChronoUnit.DAYS.between använder int
            booking.setTotalPrice(totalPrice);

            // 5- Spara Booking
            Booking savedBooking = bookingRepository.save(booking);
            return mapToResponse(savedBooking);
        }
            /* det här var inne i create metoden
            // 6. Map till BookingResponse
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
            */

    /*
        public BookingResponse getBookingByEmail(String email) {
            Booking booking = bookingRepository.findByEmail(email) // hämtar booking från repository
                    .orElseThrow(()-> new RuntimeException("Booking not found"));
            return mapToResponse(booking);
        }*/


    public List<BookingResponse> getBookingsByEmail(String email) {
        // 1 booking
        Guest guest = guestRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Guest not found"));
        // lista av booking 1 guest kan ha flera bookning
        List<Booking> bookings = bookingRepository.findAllByGuestId(guest.getId());

        return bookings.stream()
                .map(this::mapToResponse)
                .toList();
    }



   // den här ska inte använda i skolans projekt
    /*
    public List<BookingResponse> getAllBooking(){
         List<Booking> bookingList = bookingRepository.findAll();
           return bookingList.stream()
                   .map(this::mapToResponse)
                   .toList();

           }
           */


       public BookingResponse updateBooking(Long id, UpdateBookingRequest updateBookingRequest) {

           // Steg 2 . här jag  4 små stegen

           Booking booking = bookingRepository.findById(id)
                   .orElseThrow(() -> new RuntimeException("Booking not found"));

           Guest guest = guestRepository.findById(updateBookingRequest.getGuestId())
                   .orElseThrow(() -> new RuntimeException("Guest not found"));

           Room room = roomRepository.findById(updateBookingRequest.getRoomId())
                   .orElseThrow(() -> new RuntimeException("Room not found"));


           Hotel hotel = hotelRepository.findById(updateBookingRequest.getHotelId())
                   .orElseThrow(() -> new RuntimeException("Hotel not found"));

           // Steg 3 den nya information om booking


           booking.setGuest(guest);
           booking.setRoom(room);
           booking.setHotel(hotel);
           booking.setCheckInDate(updateBookingRequest.getCheckInDate());
           booking.setCheckOutDate(updateBookingRequest.getCheckOutDate());

           // Steg 4

           int nights = (int) ChronoUnit.DAYS.between(
                   booking.getCheckInDate(),
                   booking.getCheckOutDate()
           );
           booking.setTotalNights(nights);


           // Steg 5 -hämta, uppdatera, räkna, spara
           double totalPrice = nights * room.getPricePerNight();
           booking.setTotalPrice(totalPrice);

           // Steg 5- returnerar DTO med mapToResponse

           Booking savedBooking = bookingRepository.save(booking);
           return mapToResponse(savedBooking);
       }
    public void deleteBooking(Long id) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        bookingRepository.delete(booking);
    }







}