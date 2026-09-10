package com.mariza.bokning.integration;
import com.mariza.bokning.dto.Customer.CustomerResponse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerAPITest {

    @Autowired
    MockMvc mockMvc;

    @Test
     void getCustomerWithoutInternet(){
         RestTemplate restTemplate = new RestTemplate();
         MockRestServiceServer server = MockRestServiceServer.createServer(restTemplate);
         //arrange
         server.expect(requestTo("http://localhost:8080/rooms/101")) //endaste när man måste fejka en anrop till annan tjänsten
                 .andRespond(withSuccess()
                         .body("{\"id\":101,\"firstName\":\"Bad\",\"lastName\":\"Bunny\"}")
                         .contentType(MediaType.APPLICATION_JSON)
                 );

            //act
         CustomerResponse customerResponse = restTemplate.getForObject("http://localhost:8081/api/customers/1", CustomerResponse.class);
         assertEquals("Bad", customerResponse.getFirstName());
         assertEquals("Bunny", customerResponse.getLastName());

    }
}
