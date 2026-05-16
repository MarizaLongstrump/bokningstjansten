package com.mariza.hotel.controller.web;

import com.mariza.hotel.dto.account.CreateAccountRequest;
import com.mariza.hotel.entity.Account;
import com.mariza.hotel.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AccountWebController {

    private final AccountService accountService;

    public AccountWebController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/account/create")
    public String showCreateAccountForm(Model model) {
        model.addAttribute("accountRequest", new CreateAccountRequest());
        return "createAccount";
    }

    @PostMapping("/account/create")
    public String createAccount(@ModelAttribute CreateAccountRequest accountRequest) {
        accountService.createAccount(accountRequest);
        return "redirect:/rooms";
    }

    @GetMapping("/account/{id}")
    public String showDeleteAccountForm(Model model) {
        model.addAttribute("accountRequest", new CreateAccountRequest());
        return "accountDetails";
    }

    @GetMapping("/account/details/{email}")
    public String showAccountDetails(@PathVariable String email, Model model) {
        Account account = accountService.findByEmail(email);
        model.addAttribute("account", account);
        return "accountDetails";
    }




    @PostMapping("/account/delete/{id}")
    public String deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return "redirect:/rooms";
    }


}


