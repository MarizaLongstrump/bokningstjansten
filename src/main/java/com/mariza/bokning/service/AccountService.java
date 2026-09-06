package com.mariza.bokning.service;

import com.mariza.bokning.dto.Customer.CustomerRequest;
import com.mariza.bokning.dto.account.AccountResponse;
import com.mariza.bokning.dto.account.LoginRequest;
import com.mariza.bokning.entity.Account;
import com.mariza.bokning.repository.AccountRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final RestTemplate restTemplate;

    // ✅ Constructor injection – Spring skapar RestTemplate via @Bean
    public AccountService(AccountRepository accountRepository, RestTemplate restTemplate) {
        this.accountRepository = accountRepository;
        this.restTemplate = restTemplate;
    }

    // --- LOGIN ---
    public AccountResponse login(String email, String password) {
        LoginRequest request = new LoginRequest(email, password);

        AccountResponse response = restTemplate.postForObject(
                "http://customer-service:8081/customers/login",
                request,
                AccountResponse.class
        );

        if (response == null) {
            return null;
        }

        return response;
    }

    // --- CREATE ACCOUNT ---
    public Account createAccount(String email, String password, Long customerId) {
        Account account = new Account();
        account.setEmail(email);
        account.setPasswordHash(BCrypt.hashpw(password, BCrypt.gensalt()));
        account.setCustomerId(customerId);
        account.setCreatedAt(LocalDateTime.now());
        account.setUpdatedAt(LocalDateTime.now());
        return accountRepository.save(account);
    }

    // --- UPDATE CUSTOMER ---
    public void updateCustomer(CustomerRequest customerRequest) {
        String url = "http://customer-service:8081/api/customers";
        restTemplate.postForObject(url, customerRequest, Void.class);
    }
}
