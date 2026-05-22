package com.mariza.hotel.service;

import com.mariza.hotel.ResourceNotFoundException;
import com.mariza.hotel.dto.bookning.BookingResponse;
import com.mariza.hotel.dto.bookning.CreateBookingRequest;
import com.mariza.hotel.dto.bookning.UpdateBookingRequest;
import com.mariza.hotel.entity.*;
import com.mariza.hotel.repository.BookingRepository;
import com.mariza.hotel.repository.GuestRepository;
import com.mariza.hotel.repository.HotelRepository;
import com.mariza.hotel.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        bookingResponse.setBookingId(booking.getId());
        bookingResponse.setGuestFirstName(booking.getGuest().getFirstName());
        bookingResponse.setGuestLastName(booking.getGuest().getLastName());
        bookingResponse.setHotelName(booking.getHotel().getHotelName());
        bookingResponse.setRoomNumber(booking.getRoom().getRoomNumber());
        bookingResponse.setCheckInDate(booking.getCheckInDate());
        bookingResponse.setCheckOutDate(booking.getCheckOutDate());
        bookingResponse.setExtraBed(booking.getRoom().getExtraBedAvailable());
        bookingResponse.setTotalNights(booking.getTotalNights());
        bookingResponse.setTotalNights(booking.getTotalNights());
        return bookingResponse;

    }

    // create booking har
    // booking entity, guest entity,room entity, hotel entity
    // och behövs ändra return typ till mapToResponse så återkommer.

    private Guest getGuestById(Long guestId) {
        return guestRepository.findById(guestId)
                .orElseThrow(() -> new RuntimeException("Guest not found"));
    }

    private Room getRoomByRoomNumber(int roomNumber) {

               return roomRepository.findByRoomNumber(roomNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found, try again"));
    }


    private void possibleExtraBed(Room room, CreateBookingRequest createBookingRequest) {
        if (createBookingRequest.getExtraBed()){
            if (room.getRoomType() != RoomType.Double) {
                throw new RuntimeException("Extrasäng är endast tillåtet i dubbelrum");
            }
        if (!room.getExtraBedAvailable()) {
            throw new RuntimeException("Detta dubbelrum har ingen extrasäng tillgänglig");
        }

    }
}
    private Hotel getHotelById(Long hotelId) {
        return hotelRepository.findById(hotelId)
                .orElseThrow(() -> new RuntimeException("Hotel hittas ej"));
    }

    private int numberNights (CreateBookingRequest createBookingRequest) {
        long nights = ChronoUnit.DAYS.between(createBookingRequest.getCheckInDate(), createBookingRequest.getCheckOutDate());
        if (nights <=0){
            throw  new RuntimeException("Check out måste vara after check in");
        }

        return (int)nights;
    }



        public BookingResponse createBooking(CreateBookingRequest createBookingRequest) {
            // Booking booking = new Booking();

            // 1- Hämta Guest
            try {
                Guest guest = getGuestById(createBookingRequest.getGuestId());

                // 2- Hämta Room
                Room room = getRoomByRoomNumber(createBookingRequest.getRoomNumber());


                // Kontrollera lediga rum
                List<Room> availableRooms = roomRepository.findAvailableRooms(
                        createBookingRequest.getCheckInDate(),
                        createBookingRequest.getCheckOutDate()
                );

                final RoomType roomType = room.getRoomType(); // Enum

                // Om rummet kunden valt inte är ledigt → välj annat rum av samma typ
                if (!availableRooms.contains(room)) {
                    room = availableRooms.stream()
                            .filter(r -> r.getRoomType() == roomType) // Enum
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException(
                                    "Det valda rummet är upptaget och inget annat rum av samma typ är ledigt"
                            ));
                }

                possibleExtraBed(room, createBookingRequest);


                Hotel hotel = getHotelById(createBookingRequest.getHotelId());

                long nights = numberNights(createBookingRequest);
                ;
                double totalPrice = nights * room.getPricePerNight();


                // 4- Skapa Booking
                Booking booking = new Booking();
                booking.setGuest(guest);
                booking.setRoom(room);
                booking.setHotel(hotel);
                booking.setCheckInDate(createBookingRequest.getCheckInDate());
                booking.setCheckOutDate(createBookingRequest.getCheckOutDate());
                booking.setExtraBed(createBookingRequest.getExtraBed());
                booking.setTotalNights((int) nights); //ChronoUnit.DAYS.between använder int
                booking.setTotalPrice(totalPrice);

                // 5- Spara Booking
                Booking savedBooking = bookingRepository.save(booking);
                return mapToResponse(savedBooking);
            }catch (Exception e){
                System.out.println("input mismatching");
                throw new ResourceNotFoundException("input mismatching- try again");
            }

    }

        public BookingResponse getBookingById(Long bookingId) {
            return mapToResponse(bookingRepository.findById(bookingId)
                    .orElseThrow(() -> new RuntimeException("Booking not found")));

        }




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



   // använder i roomWebService för att märkera vilka room som är bokat.

    public List<BookingResponse> getAllBookings(){
         List<Booking> bookingList = bookingRepository.findAll();
           return bookingList.stream()
                   .map(this::mapToResponse)
                   .toList();

           }



       public BookingResponse updateBooking(Long id, UpdateBookingRequest updateBookingRequest) {

           // Steg 2 . här jag  4 små stegen

           Booking booking = bookingRepository.findById(id)
                   .orElseThrow(() -> new RuntimeException("Booking not found"));

           Guest guest = guestRepository.findById(booking.getGuest().getId())
                   .orElseThrow(() -> new RuntimeException("Guest not found"));
            /*
           Room room = roomRepository.findById(updateBookingRequest.getRoomId())
                   .orElseThrow(() -> new RuntimeException("Room not found"));
           */

           Room room = roomRepository.findByRoomNumber(updateBookingRequest.getRoomNumber())
                   .orElseThrow(() -> new RuntimeException("Room not found"));

           Hotel hotel = hotelRepository.findById(booking.getHotel().getId())
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