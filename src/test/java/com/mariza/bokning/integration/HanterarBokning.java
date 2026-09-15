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
@TestPropertySource(properties = "spring.datasource.username=root")
@TestPropertySource(properties = "spring.datasource.password=root")
@AutoConfigureMockMvc
class HanterarBokning {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void hanterarHotel() throws Exception {

        postHotel();

        mockMvc.perform(get("/hotel/" + "Grand Hotel"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name") // dolar tecken för att letar efter hotelId i json
                        .value("Grand Hotel"));

    }
        @Test
        void hanterarRoom() throws Exception{
        postHotel();

        mockMvc.perform(post("/room")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"roomNumber\": 101," +
                        "\"floor\": 1," +
                        "\"roomType\": \"Single\"," +
                        "\"pricePerNight\": 40.5," +
                        "\"hotelName\": \"Grand Hotel\"," +
                        "\"clean\": false}")
                .characterEncoding(StandardCharsets.UTF_8))
                .andExpect(status().isOk());

        mockMvc.perform(get("/room/101"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.roomNumber")
                .value(101));

        mockMvc.perform(delete("/room/roomNumber/101"))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/hotel/"+"Grand Hotel"))
                .andExpect(status().isOk());

    }

    void postHotel() throws Exception {
        mockMvc.perform(post("/hotel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Grand Hotel\"," +
                                "\"adress\":\"Stockholmgatan\"," +
                                "\"city\": \"Stockholm\"}")
                        .characterEncoding("UTF-8"))
                .andExpect(status().isOk());
    }


}
