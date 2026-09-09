package com.mariza.bokning.controller.web;

import com.mariza.bokning.dto.Customer.CustomerResponse;
import com.mariza.bokning.dto.account.AccountResponse;
import com.mariza.bokning.dto.Customer.CustomerRequest;
import com.mariza.bokning.service.AccountService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/account")
public class AccountWebController {

    private final AccountService accountService;

    public AccountWebController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/details")
    public String showDetails(Model model, HttpSession session) {
        RestTemplate customerServiceCall = new RestTemplate();
        String url = "http://customer-service:8081/api/customers/" + session.getAttribute("customerId");
        model.addAttribute("accountId", session.getAttribute("customerId"));
        CustomerResponse customer =
                customerServiceCall.getForObject("http://customer-service:8081/api/customers/" + session.getAttribute("customerId"),
                        CustomerResponse.class);

        model.addAttribute("guestInloggade", customer.getFirstName() + " " +
                customer.getLastName());
        return "accountDetails";
    }

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
    @PostMapping("/logout")
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

    @PostMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id, Model model) {

        RestTemplate customerServiceCall = new RestTemplate();
        String url = "http://customer-service:8081/api/customers/" + id;

        try {
            customerServiceCall.delete(url);

            model.addAttribute("message", "Account deleted successfully");
            return "home";

        } catch (HttpClientErrorException ex) {

            if (ex.getStatusCode() == HttpStatus.CONFLICT) {

                CustomerResponse customer =
                        customerServiceCall.getForObject("http://customer-service:8081/api/customers/" + id,
                        CustomerResponse.class);

                model.addAttribute("guestInloggade", customer.getFirstName() + " " +
                        customer.getLastName());
                model.addAttribute("message",
                        "Account cannot be deleted because the customer has active bookings");

                return "accountDetails";
            }

            model.addAttribute("message", "Customer service error. Details: "
                + ex.getStatusCode() + " " + ex.getMessage());
            return "accountDetails";
        }
    }

}
