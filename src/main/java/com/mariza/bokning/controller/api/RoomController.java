package com.mariza.bokning.controller.api;

import com.mariza.bokning.dto.Room.CreateRoomRequest;
import com.mariza.bokning.dto.Room.RoomResponse;
import com.mariza.bokning.dto.Room.UpdateRoomRequest;
import com.mariza.bokning.entity.Room;
import com.mariza.bokning.service.RoomService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<Room> getAllRooms() {
        return roomService.findAllRooms();
    }

    @GetMapping("/{roomNumber}")
    public RoomResponse getById(
        @PathVariable int roomNumber) {
        return roomService.getRoomByRoomNumber(roomNumber);

    }

    @PostMapping
    public Room createRoom(
        @RequestBody CreateRoomRequest createRoomRequest) {
        return roomService.createRoomFromAPI(createRoomRequest);

    }

    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable Long id) {
        roomService.deleteRoomById(id);
    }

    @DeleteMapping("/roomNumber/{roomNumber}")
    public void deleteRoomByRoomNumber
        (@PathVariable int roomNumber) {
        roomService.deleteRoomByRoomNumber(roomNumber);
    }


    @PutMapping("/{id}")
    public Room updateRoom(@PathVariable Long id,
        @RequestBody UpdateRoomRequest request) {
        return roomService.updateRoom(id,request);
    }


    // tillgånglig rum
    @GetMapping("/available")
    public List<RoomResponse> getAvailableRooms(
       @RequestParam LocalDate start,
       @RequestParam LocalDate end) {
       return roomService.searchAvailableRooms(start, end);
    }

}
