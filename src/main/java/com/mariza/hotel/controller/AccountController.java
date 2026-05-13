package com.mariza.hotel.controller;

import com.mariza.hotel.dto.account.CreateAccountRequest;
import com.mariza.hotel.dto.account.UpdateAccountRequest;
import com.mariza.hotel.entity.Account;
import com.mariza.hotel.service.AccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
    public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{email}")
    public Account getAccountByEmail(@PathVariable String email) {
        return accountService.findByEmail(email);
    }

    @PostMapping
    public boolean createAccount(@RequestBody CreateAccountRequest createAccountRequest) {
        return accountService.createAccount(createAccountRequest);
    }

    @PutMapping("/{id}")
    public Account updateAccount(@PathVariable Long id, @RequestBody UpdateAccountRequest updateAccountRequest) {
        return accountService.updateAccount(id, updateAccountRequest);
    }

    @DeleteMapping ("/{id}")
    public void deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
    }

}
