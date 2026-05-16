package com.mariza.hotel.controller.api;

import com.mariza.hotel.dto.Room.CreateRoomRequest;
import com.mariza.hotel.dto.Room.RoomResponse;
import com.mariza.hotel.dto.Room.UpdateRoomRequest;
import com.mariza.hotel.entity.Room;
import com.mariza.hotel.service.RoomService;
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

    // den här på ADMIN nivå

    @GetMapping
    public List<Room> getAllRooms() {
        return roomService.findAllRooms();
    }

    @GetMapping("/{roomNumber}")
    public RoomResponse getById(@PathVariable int roomNumber) {
        return roomService.getRoomByRoomNumber(roomNumber);

    }


    @PostMapping
    public Room createRoom(@RequestBody CreateRoomRequest createRoomRequest) {
        return roomService.createRoomFromAPI(createRoomRequest);

    }

    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable Long id) {
        roomService.deleteRoomById(id);
    }
    @PutMapping("/{id}")
    public Room updateRoom(@PathVariable Long id,@RequestBody UpdateRoomRequest request) {
        return roomService.updateRoom(id,request);
    }
    // tillgångli rum
    @GetMapping("/available")
    public List<Room> getAvailableRooms(
            @RequestParam LocalDate start,
            @RequestParam LocalDate end) {
        return roomService.searchAvailableRooms(start, end);
    }


}
