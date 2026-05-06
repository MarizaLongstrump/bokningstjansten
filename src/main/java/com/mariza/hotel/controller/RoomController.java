package com.mariza.hotel.controller;

import com.mariza.hotel.dto.CreateRoomRequest;
import com.mariza.hotel.dto.UpdateRoomRequest;
import com.mariza.hotel.entity.Room;
import com.mariza.hotel.service.RoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
    public class RoomController {

    private RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<Room> findAllRooms() {
        return roomService.findAllRooms();
    }

    @GetMapping("/{id}")
    public Room findById(@PathVariable Long id) {
        return roomService.findRoomById(id);
    }

    @PostMapping
    public Room createRoom(@RequestBody CreateRoomRequest createRoomRequest) {
        return roomService.createRoom(createRoomRequest);

    }

    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable Long id) {
        roomService.deleteRoomById(id);
    }
    @PutMapping("/{id}")
    public Room updateRoom(@PathVariable Long id,@RequestBody UpdateRoomRequest request) {
        return roomService.updateRoom(id,request);
    }

}
