package com.mariza.bokning.integration;
import com.mariza.bokning.dto.customer.CustomerResponse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerAPITest {

    @Autowired
    MockMvc mockMvc;

    @Container
    static MySQLContainer<?> mySQLContainer =
            new  MySQLContainer<>("mysql:8.0.36");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
    }

    @Test
     void getCustomerWithoutInternet(){
         RestTemplate restTemplate = new RestTemplate();
         MockRestServiceServer server = MockRestServiceServer.createServer(restTemplate);
         //arrange
         server.expect(requestTo("/customers/1")) //endaste när man måste fejka en anrop till annan tjänsten
                 .andRespond(withSuccess()
                         .body("{\"id\":101,\"firstName\":\"Bad\",\"lastName\":\"Bunny\"}")
                         .contentType(MediaType.APPLICATION_JSON)
                 );

            //act
         CustomerResponse customerResponse = restTemplate.getForObject("/customers/1", CustomerResponse.class);

        assertEquals("Bad", customerResponse.getFirstName());
        assertEquals("Bunny", customerResponse.getLastName());
        server.verify();
    }
}
