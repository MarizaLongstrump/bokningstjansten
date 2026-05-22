package com.mariza.hotel.controller.web;

import com.mariza.hotel.dto.account.CreateAccountRequest;
import com.mariza.hotel.entity.Account;
import com.mariza.hotel.service.AccountService;
import jakarta.servlet.http.HttpSession;
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
        return "redirect:/rooms";

    }
    /*
    @PostMapping("/account/create")
    public String createAccount(@ModelAttribute CreateAccountRequest accountRequest) {
        accountService.createAccount(accountRequest);
        return "redirect:/account/details/" + accountRequest.getEmail();
    }
*/




    // för att hämta detaljer från ett konto
    @GetMapping("/account/details")
    public String showAccountDetails(HttpSession session,  Model model) {
        Object username= session.getAttribute("username");
        if (username == null) { // om man inte är inloggade
            return "redirect:/login";
        }
        Account account = accountService.findByEmail((String)username);
                                    //  ****  VIKTIG  ***
        if (account == null) {  // **** ANALYSERAR VAD SOM SKA HÄNDA ****
            return "redirect:/";//*** OM CATCH HAR FÅNGAT ETT FEL ***
        }

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
                        Model model, HttpSession session) {

        Account account = accountService.login(email, password);

        if (account == null) {
            model.addAttribute("error", "Fel email eller lösenord");
            return "login";
        }

        session.setAttribute("userId", account.getId());
        session.setAttribute("username", account.getEmail());


        model.addAttribute("account", account);
        return "redirect:/rooms";
    }

}


