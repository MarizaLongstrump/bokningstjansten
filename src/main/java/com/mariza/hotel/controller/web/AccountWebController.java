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
    // för förmulär
    @GetMapping("/account/create")
    public String showCreateAccountForm(Model model) {
        model.addAttribute("accountRequest", new CreateAccountRequest());
        return "createAccount";
    }
    // för att anropa metod create som är i serviceAccount
    // createMetod- SUBMIT METOD
    @PostMapping("/account/create")
    public String createAccount(@ModelAttribute CreateAccountRequest accountRequest) {
        accountService.createAccount(accountRequest);
        return "redirect:/romms";

    }
    /*
    @PostMapping("/account/create")
    public String createAccount(@ModelAttribute CreateAccountRequest accountRequest) {
        accountService.createAccount(accountRequest);
        return "redirect:/account/details/" + accountRequest.getEmail();
    }
*/




    // för att hämta detaljer från ett konto
    @GetMapping("/account/details/{email}")
    public String showAccountDetails(@PathVariable String email, Model model) {
        Account account = accountService.findByEmail(email);
        model.addAttribute("account", account);
        return "accountDetails";
    }


    @PostMapping("/account/delete/{id}")
    public String deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return "redirect:/account/create";
    }

    @GetMapping("/account/login")
    public String showLoginForm() {
        return "login";
    }
    @PostMapping("/account/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        Model model) {

        Account account = accountService.login(email, password);

        if (account == null) {
            model.addAttribute("error", "Fel email eller lösenord");
            return "login/rooms";
        }

        model.addAttribute("account", account);
        return "accountHome";
    }

}


