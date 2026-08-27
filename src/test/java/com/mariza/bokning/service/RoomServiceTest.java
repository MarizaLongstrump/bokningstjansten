package com.mariza.bokning.service;

import com.mariza.bokning.dto.Room.CreateRoomRequest;
import com.mariza.bokning.entity.Room;
import com.mariza.bokning.entity.RoomType;
import com.mariza.bokning.repository.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.mockito.ArgumentCaptor;


@ExtendWith(MockitoExtension.class)
class RoomServiceTest {

    @Mock
    // deklarerar RoomRepository
    // deklarerar RooService
    private RoomRepository roomRepository;
    private RoomService roomService;


    @BeforeEach
    void setUp() {
        roomService = new RoomService(roomRepository);

    }
    //ArgumentCaptor gör att jag kan fånga det Room objekt som service skapar från
    // min fake data. På så sätt kan jag testa logiken
    // i service samtidigt som jag använder mockat repository
    @Test
    void doesCreateRoomFromAPIReturnRoomRepositorWithDoubleRoom() {
        ArgumentCaptor<Room> roomCaptor = ArgumentCaptor.forClass(Room.class);
        // arrange -- skapar fake data
        // ska testa true
        CreateRoomRequest createRoomRequest = new CreateRoomRequest();
        createRoomRequest.setHotelId(1L);
        createRoomRequest.setRoomNumber(100);
        createRoomRequest.setFloor(1);
        createRoomRequest.setRoomType(RoomType.Double);
        createRoomRequest.setPricePerNight(1845.32);
        createRoomRequest.setClean(true);

        // arrange -- ska använda fake data
        Room room1 = new Room();
        room1.setHotelId(1L);
        room1.setRoomNumber(100);
        room1.setFloor(1);
        room1.setRoomType(RoomType.Double);
        room1.setPricePerNight(1845.32);
        room1.setClean(true);
        room1.setExtraBedAvailable(true);

        when(roomRepository.save(roomCaptor.capture())).thenAnswer(invocation -> invocation.getArgument(0));
        // act
        roomService.createRoomFromAPI(createRoomRequest);
        // assert
        Room capturedRoom = roomCaptor.getValue();
        assertNotNull(capturedRoom);
        assertEquals(1L,capturedRoom.getHotelId());
        assertEquals(100,capturedRoom.getRoomNumber());
        assertEquals(1,capturedRoom.getFloor());
        assertEquals(RoomType.Double,capturedRoom.getRoomType());
        assertEquals(true,capturedRoom.getExtraBedAvailable(), "Extra bed"); // här blev det intressant
        assertEquals(1845.32,capturedRoom.getPricePerNight());
        assertEquals(true,capturedRoom.isClean(), "Is Clean");

    }


    @Test
    void doesCreateRoomFromAPIReturnRoomRepositorWithSingleRoom() {
        ArgumentCaptor<Room> roomCaptor = ArgumentCaptor.forClass(Room.class);
        CreateRoomRequest createRoomRequest = new CreateRoomRequest();
        createRoomRequest.setHotelId(1L);
        createRoomRequest.setRoomNumber(100);
        createRoomRequest.setFloor(1);
        createRoomRequest.setRoomType(RoomType.Single);
        createRoomRequest.setPricePerNight(1845.32);
        createRoomRequest.setClean(false);

        when(roomRepository.save(roomCaptor.capture())).thenAnswer(invocation -> invocation.getArgument(0));
        // act
        roomService.createRoomFromAPI(createRoomRequest);
        // assert
        Room capturedRoom = roomCaptor.getValue();
        assertNotNull(capturedRoom);
        assertEquals(1L,capturedRoom.getHotelId());
        assertEquals(100,capturedRoom.getRoomNumber());
        assertEquals(1,capturedRoom.getFloor());
        assertEquals(RoomType.Single,capturedRoom.getRoomType());
        assertNotEquals(true,capturedRoom.getExtraBedAvailable(), "No extra bed"); // här blev det intressant
        assertEquals(1845.32,capturedRoom.getPricePerNight());
        assertNotEquals(true,capturedRoom.isClean(), "Is not Clean");

    }
}