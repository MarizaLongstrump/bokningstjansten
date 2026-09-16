package com.mariza.bokning.integration;


import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.context.ImportTestcontainers;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import java.nio.charset.StandardCharsets;

import org.springframework.web.service.registry.ImportHttpServices;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;

import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@Testcontainers
@SpringBootTest
@Transactional // annars körs inte delete test- måste läsa mer om trasactional databas.
//@TestPropertySource(properties = "spring.datasource.url=jdbc:mysql://localhost:3306/bookingTest")
//@TestPropertySource(properties = "spring.datasource.username=root")
//@TestPropertySource(properties = "spring.datasource.password=root")
@AutoConfigureMockMvc
class HanterarBokning {

    @Autowired
    private MockMvc mockMvc;

    @Container
    static MySQLContainer<?> mySQLContainer =
            new  MySQLContainer<>("mysql:8.0.36");
               //     .withDatabaseName("bookingTest")
              //      .withUsername("test") //
              //      .withPassword("secret"); // hamtar från aplication properties

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
    }

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
