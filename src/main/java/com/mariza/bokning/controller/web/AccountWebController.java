package com.mariza.bokning.controller.web;

import com.mariza.bokning.dto.account.AccountResponse;
import com.mariza.bokning.dto.Customer.CustomerRequest;
import com.mariza.bokning.service.AccountService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/account")
public class AccountWebController {

    private final AccountService accountService;

    public AccountWebController(AccountService accountService) {
        this.accountService = accountService;
    }

    // --- LOGIN ---
    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // rätt filnamn
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        AccountResponse account = accountService.login(email, password);

        if (account == null) {
            model.addAttribute("error", "Fel email eller lösenord");
            return "login";
        }

        session.setAttribute("customerId", account.getCustomerId());
        session.setAttribute("email", account.getEmail());

        return "redirect:/rooms";
    }

    // --- LOGOUT ---
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    // --- UPDATE CUSTOMER INFO ---
    @PostMapping("/updateCustomer")
    public String updateCustomer(@ModelAttribute CustomerRequest customerRequest) {
        accountService.updateCustomer(customerRequest);
        return "redirect:/account/details";
    }
}
