package com.mariza.hotel.service;

import com.mariza.hotel.dto.hotel.CreateHotelRequest;
import com.mariza.hotel.dto.hotel.UpdateHotelRequest;
import com.mariza.hotel.entity.Hotel;
import com.mariza.hotel.repository.HotelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


// aktiverar Mockito i JUnit5 så kan Mock fungera
@ExtendWith(MockitoExtension.class)
class HotelServiceTest {

    @Mock
    // för att skapa en fake repository som inte pratar med databas.
    // mocken returnerar fake data som jag skapar
    // kontrolleras om sabe anropas
    // gör test snabb och isolerat
    private HotelRepository hotelRepository; // mock repository
    private HotelService hotelService;


    // beforeEach - körs before varje test
    @BeforeEach
    void setUp() {
        // instansierar Hotel och ger den mock repository
        hotelService = new HotelService(hotelRepository);
    }
    @Test
    void doesCreateHotelReturnHotelRepository() {
        // 1.1 - arrange - skapar man fake data för att simmulera test
        CreateHotelRequest createHotelRequest = new CreateHotelRequest();
        createHotelRequest.setName("Marizas Hotel");
        createHotelRequest.setAdress("Hotel gatan 32");
        createHotelRequest.setCity("MarizasLand");


        // 1.2 - arrange - här användar man fake data som man skapades
        Hotel savedHotel = new Hotel();
        savedHotel.setHotelName("Marizas Hotel");
        savedHotel.setHotelAddress("Hotel gatan 32");
        savedHotel.setHotelCity("MarizasLand");

        when(hotelRepository.save(any(Hotel.class))).thenReturn(savedHotel);

        // 2. act
        Hotel result = hotelService.createHotel(createHotelRequest);

        // assert
        assertNotNull(result);
        assertEquals ("Marizas Hotel", result.getHotelName());
        assertEquals("Hotel gatan 32", result.getHotelAddress());
        assertEquals("MarizasLand", result.getHotelCity());
        verify(hotelRepository, times(1)).save(any(Hotel.class));

    }

    @Test
    void doesUpdateHotelWorks() {
        //  arrange
        Long Id= 100L;
        UpdateHotelRequest updateHotelRequest = new UpdateHotelRequest();
        // skapar fake data

        updateHotelRequest.setName("Marizas Hotel Five Stars");
        updateHotelRequest.setAddress("Gatovägen 33");
        updateHotelRequest.setCity("GatoLand");

        // användar fake data
        Hotel savedHotelUpdate = new Hotel();
        savedHotelUpdate.setHotelName("Marizas Hotel Five Stars");
        savedHotelUpdate.setHotelAddress("Gatovägen 33");
        savedHotelUpdate.setHotelCity("GatoLand");
        when(hotelRepository.save(any(Hotel.class))).thenReturn(savedHotelUpdate);
        when(hotelRepository.findById(Id)).thenReturn(Optional.of(savedHotelUpdate));
        // act
       Hotel updateResults = hotelService.updateHotel(100L,updateHotelRequest);

       // assert
        assertNotNull(updateResults);
        assertEquals("Marizas Hotel Five Stars", updateResults.getHotelName());
        assertEquals("Gatovägen 33", updateResults.getHotelAddress());
        assertEquals("GatoLand", updateResults.getHotelCity());

    }
}