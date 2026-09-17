package com.mariza.bokning.service;

import com.mariza.bokning.dto.customer.CustomerRequest;
import com.mariza.bokning.dto.account.AccountResponse;
import com.mariza.bokning.dto.account.LoginRequest;
import com.mariza.bokning.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final RestTemplate restTemplate;

    @Value("${customer-service.url}")
    private String customerServiceUrl;


    public AccountService(AccountRepository accountRepository, RestTemplate restTemplate) {
        this.accountRepository = accountRepository;
        this.restTemplate = restTemplate;
    }

    public AccountResponse login(String email, String password) {
        LoginRequest request = new LoginRequest(email, password);
        // eller det om dto loginRequest inte hade konstruktör
       // request.setEmail(email);
      //  request.setPassword(password);


        AccountResponse response = restTemplate.postForObject(
                 customerServiceUrl + "customer/login/",
              //  "http://customer-service:8081/customers/login",
                request,
                AccountResponse.class
        );

        if (response == null) {
            return null;
        }

        return response;
    }

    public void updateCustomer(CustomerRequest customerRequest) {
        String url = "http://customer-service:8081/api/customers";
        restTemplate.postForObject(url, customerRequest, Void.class);
    }
}
