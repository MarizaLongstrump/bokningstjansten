package com.mariza.hotel.service;

import com.mariza.hotel.dto.Room.CreateRoomRequest;
import com.mariza.hotel.dto.Room.RoomResponse;
import com.mariza.hotel.dto.Room.UpdateRoomRequest;
import com.mariza.hotel.dto.guest.GuestResponse;
import com.mariza.hotel.entity.Room;
import com.mariza.hotel.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
    public class RoomService {


    private RoomRepository roomRepository;


        public RoomService(RoomRepository roomRespository) {
            this.roomRepository = roomRespository;
        }

        // den här ska inte använda i den här projekt
       public List<Room> findAllRooms() {
            return roomRepository.findAll();
       }

       public Room findRoomById(Long id) {
            return roomRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Room not found- klass RoomService"));

       }

       public Room createRoom (CreateRoomRequest createRoomRequest) {
            Room room = new Room();
            room.setRoomNumber(createRoomRequest.getRoomNumber());
            room.setFloor(createRoomRequest.getFloor());
            room.setRoomType(createRoomRequest.getRoomType());
            room.setPricePerNight(createRoomRequest.getPricePerNight());
            room.setClean(createRoomRequest.getClean());

            return roomRepository.save(room);
       }

       public void deleteRoomById(Long id) {
            roomRepository.deleteById(id);
       }

       public Room updateRoom(Long id,UpdateRoomRequest updateRoomRequest) {
            Room room = roomRepository.findById(id)
                    .orElseThrow(()-> new RuntimeException("Room not found - RoomService klas"));
            room.setRoomNumber(updateRoomRequest.getRoomNumber());
            room.setFloor(updateRoomRequest.getFloor());
            room.setRoomType(updateRoomRequest.getRoomType());
            room.setPricePerNight(updateRoomRequest.getPricePerNight());
            room.setClean(updateRoomRequest.getClean());
            return roomRepository.save(room);
       }

       public RoomResponse getRoomByRoomNumber(int roomNumber) {
            Room room = roomRepository.findByRoomNumber(roomNumber)
                    .orElseThrow(()-> new RuntimeException("Room not found- klass RoomService"));
            RoomResponse roomResponse = new RoomResponse();
            roomResponse.setRoomNumber(room.getRoomNumber());
            roomResponse.setRoomType(room.getRoomType());
            roomResponse.setPricePerNight(room.getPricePerNight());
            return  roomResponse;

        }


}
