package com.mariza.bokning.service;

import com.mariza.bokning.dto.room.CreateRoomRequest;
import com.mariza.bokning.dto.room.RoomResponse;
import com.mariza.bokning.dto.room.UpdateRoomRequest;
import com.mariza.bokning.entity.Booking;
import com.mariza.bokning.entity.Hotel;
import com.mariza.bokning.entity.Room;
import com.mariza.bokning.entity.RoomType;
import com.mariza.bokning.repository.HotelRepository;
import com.mariza.bokning.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;


@Service
    public class RoomService {


    private final HotelRepository hotelRepository;
    private RoomRepository roomRepository;


    public RoomService(RoomRepository roomRespository, HotelRepository hotelRepository) {
        this.roomRepository = roomRespository;
        this.hotelRepository = hotelRepository;
    }

    //

    public List<Room> findAllRooms() {

        return roomRepository.findAll().stream()
                .sorted(Comparator.comparing(Room::getRoomType)
                        .thenComparing(Room::getPricePerNight))
                .toList();

    }

    public Room findRoomById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found- klass RoomService"));

    }

    // pga det finns if satser ska jag testar 2 gånger samma metod
    // 1 med true med double bed
    // 1 med false utan double bed
    public Room createRoomFromAPI(CreateRoomRequest createRoomRequest) {


        Hotel hotel = hotelRepository.findByHotelName(createRoomRequest.getHotelName())
                .orElseThrow(()-> new RuntimeException("Hotel not found"));

        Room room = new Room();
        if (createRoomRequest.getRoomType() == RoomType.Double) {
            room.setExtraBedAvailable(true); // 1 test
        } else {
            room.setExtraBedAvailable(false); // 1 test
        }

        room.setHotel(hotel);// efter ändring i entity
        room.setRoomNumber(createRoomRequest.getRoomNumber());
        room.setFloor(createRoomRequest.getFloor());
        room.setRoomType(createRoomRequest.getRoomType());
        room.setPricePerNight(createRoomRequest.getPricePerNight());
        room.setClean(createRoomRequest.getClean());

        return roomRepository.save(room);
    }

    // om jag skulle göra det från Admin med websidan
    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }


    public void deleteRoomById(Long id) {

        roomRepository.deleteById(id);
    }

    public void deleteRoomByRoomNumber(int roomNumber) {
        roomRepository.deleteByRoomNumber(roomNumber);
    }

    public Room updateRoom(Long id, UpdateRoomRequest updateRoomRequest) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found - RoomService klas"));

        if (updateRoomRequest.getRoomType() == RoomType.Double) {
            room.setExtraBedAvailable(true);
        } else {
            room.setExtraBedAvailable(false);
        }

        room.setRoomNumber(updateRoomRequest.getRoomNumber());
        room.setFloor(updateRoomRequest.getFloor());
        room.setRoomType(updateRoomRequest.getRoomType());
        room.setPricePerNight(updateRoomRequest.getPricePerNight());
        room.setClean(updateRoomRequest.getClean());
        return roomRepository.save(room);
    }

    public RoomResponse getRoomByRoomNumber(int roomNumber) {
        Room room = roomRepository.findByRoomNumber(roomNumber)
                .orElseThrow(() -> new RuntimeException("Room not found- klass RoomService"));
        RoomResponse roomResponse = new RoomResponse();
        roomResponse.setRoomNumber(room.getRoomNumber());
        roomResponse.setFloor(room.getFloor());
      //  roomResponse.setId(room.getId());
        roomResponse.setRoomType(room.getRoomType());
        roomResponse.setPricePerNight(room.getPricePerNight());
        return roomResponse;

    }

    // VG - Besckikbaar room
    public RoomResponse mapToResponse(Room room) {

        System.out.println("DEBUG ROOM -> id=" + room.getId() + " roomNumber=" + room.getRoomNumber());
        RoomResponse response = new RoomResponse();
      //  response.setId(room.getId());
        response.setRoomNumber(room.getRoomNumber());
        response.setFloor(room.getFloor());
        response.setRoomType(room.getRoomType());
        response.setPricePerNight(room.getPricePerNight());
        response.setExtraBedAvailable(room.getExtraBedAvailable());

        // Om rummet har bokningar, visa senaste bokningen
        if (room.getBookings() != null && !room.getBookings().isEmpty()) {
            Booking latest = room.getBookings().get(room.getBookings().size() - 1);
            response.setCheckInDate(latest.getCheckInDate());
            response.setCheckOutDate(latest.getCheckOutDate());
            response.setOccupied(true);
        } else {
            response.setCheckInDate(null);
            response.setCheckOutDate(null);
            response.setOccupied(false);
        }

        return response;
    }


    /*
    public RoomResponse mapToResponse(Room room) {
        RoomResponse roomResponse = new RoomResponse();
        roomResponse.setRoomNumber(room.getRoomNumber());
        roomResponse.setFloor(room.getFloor());
        roomResponse.setRoomType(room.getRoomType());
        roomResponse.setPricePerNight(room.getPricePerNight());
        roomResponse.setExtraBedAvailable(room.getExtraBedAvailable());
        roomResponse.setPricePerNight(room.getPricePerNight());
        return roomResponse;
    }
*/
    public List<RoomResponse> searchAvailableRooms(LocalDate start, LocalDate end) {
        List<Room> roomList = roomRepository.findAll();
        return roomList.stream()
                .map(this::mapToResponse)
                .toList();

    }

    public List<RoomResponse> getAllRooms() {
        List<Room> roomList = roomRepository.findAll();
        return roomList.stream()
                .map(this::mapToResponse)
                .toList();
    }


}




