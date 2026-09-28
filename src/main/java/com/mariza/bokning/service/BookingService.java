package com.mariza.bokning.service;

import com.mariza.bokning.ResourceNotFoundException;
import com.mariza.bokning.dto.bookning.BookingResponse;
import com.mariza.bokning.dto.bookning.CreateBookingRequest;
import com.mariza.bokning.dto.bookning.UpdateBookingRequest;
import com.mariza.bokning.entity.*;
import com.mariza.bokning.repository.BookingRepository;
import com.mariza.bokning.repository.HotelRepository;
import com.mariza.bokning.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;


import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BookingService {

    private RoomRepository roomRepository;
    private HotelRepository hotelRepository;
    private BookingRepository bookingRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${customer-service.url}")
    private String customerServiceUrl;

    public BookingService( RoomRepository roomRepository, HotelRepository hotelRepository, BookingRepository bookingRepository) {

        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
        this.bookingRepository = bookingRepository;
    }

    public BookingResponse mapToResponse(Booking booking) {
        BookingResponse bookingResponse = new BookingResponse(); // skapar en ny DTO objekt som skickas till postman
        bookingResponse.setBookingId(booking.getId());
        bookingResponse.setCustomerId(booking.getCustomerId());
        bookingResponse.setHotelId(booking.getHotel().getId());
        bookingResponse.setRoomNumber(booking.getRoom().getRoomNumber());
        bookingResponse.setCheckInDate(booking.getCheckInDate());
        bookingResponse.setCheckOutDate(booking.getCheckOutDate());
        bookingResponse.setExtraBed(booking.getRoom().getExtraBedAvailable());
        bookingResponse.setTotalNights(booking.getTotalNights());
        bookingResponse.setTotalNights(booking.getTotalNights());
        return bookingResponse;

    }

    private Room getRoomByRoomNumber(int roomNumber) {

               return roomRepository.findByRoomNumber(roomNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Room " + roomNumber + " not found, try again"));
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


            // Kontrollera att kunden finns via kundtjänsten
            // Rest-anrop till kundtjänsten
            String url = customerServiceUrl + "customers/" + createBookingRequest.getCustomerId();
           // String url = "http://customer-service:8081/api/customers/" + createBookingRequest.getCustomerId();


            try {
                ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

                if (!response.getStatusCode().is2xxSuccessful()) {
                    throw new RuntimeException("Kunden hittades inte i kundtjänsten");
                }

            } catch (Exception ex) {
                throw new RuntimeException("Kundtjänsten är nere. Försök igen senare.");
            }
            Booking booking = new Booking();

            // 1- Hämta rummet
            Room room = getRoomByRoomNumber(createBookingRequest.getRoomNumber());
            if (room == null) {
                throw new SecurityException("Room does not exist");
            }


            booking.setCustomerId(createBookingRequest.getCustomerId());



            try {
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
               // Booking booking = new Booking();
                booking.setCustomerId(createBookingRequest.getCustomerId());
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




    public List<BookingResponse> getBookingsByCustomerId(Long customerId) {
        List<Booking> bookings = bookingRepository.findAllByCustomerId(customerId);
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


           Booking booking = bookingRepository.findById(id)
                   .orElseThrow(() -> new RuntimeException("Booking not found"));

           booking.setCustomerId((updateBookingRequest.getCustomerId()));
            /*
           Room room = roomRepository.findById(updateBookingRequest.getRoomId())
                   .orElseThrow(() -> new RuntimeException("Room not found"));
           */

           Room room = roomRepository.findByRoomNumber(updateBookingRequest.getRoomNumber())
                   .orElseThrow(() -> new RuntimeException("Room not found"));

           Hotel hotel = hotelRepository.findById(booking.getHotel().getId())
                   .orElseThrow(() -> new RuntimeException("Hotel not found"));



           booking.setCustomerId((updateBookingRequest.getCustomerId()));
           booking.setRoom(room);
           booking.setHotel(hotel);
           booking.setCheckInDate(updateBookingRequest.getCheckInDate());
           booking.setCheckOutDate(updateBookingRequest.getCheckOutDate());



           int nights = (int) ChronoUnit.DAYS.between(
                   booking.getCheckInDate(),
                   booking.getCheckOutDate()
           );
           booking.setTotalNights(nights);


           double totalPrice = nights * room.getPricePerNight();
           booking.setTotalPrice(totalPrice);


           Booking savedBooking = bookingRepository.save(booking);
           return mapToResponse(savedBooking);
       }
    public void deleteBooking(Long id) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        bookingRepository.delete(booking);
    }



}