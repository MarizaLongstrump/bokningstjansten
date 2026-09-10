package com.mariza.bokning.integration;


import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional // annars körs inte delete test- måste läsa mer om trasactional databas.
@TestPropertySource(properties = "spring.datasource.url=jdbc:mysql://localhost:3306/bookingTest")
@AutoConfigureMockMvc
class TestarHamtarRoom {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void hamtarRoom() throws Exception{
        mockMvc.perform(post("/room")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"roomNumber\": 101," +
                        "\"floor\": 1," +
                        "\"roomType\": \"Single\"," +
                        "\"pricePerNight\": 40.5," +
                        "\"hotelId\": 1," +
                        "\"clean\": false," +
                        "\"id\": 1 }")
                .characterEncoding(StandardCharsets.UTF_8))
                .andExpect(status().isOk());

        mockMvc.perform(get("/room/101"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.roomNumber")
                .value(101));

        mockMvc.perform(delete("/room/roomNumber/101"))
                .andExpect(status().isOk());

    }
}
