package com.mariza.bokning.controller.web;

import com.mariza.bokning.dto.customer.CustomerRequest;
import com.mariza.bokning.dto.customer.CustomerResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


@Controller
    @RequestMapping("/customer")
    public class CustomerWebController {

        @GetMapping("/register")
        public String showRegisterForm(Model model) {
            model.addAttribute("customer", new CustomerRequest());
            return "register";
        }

        @PostMapping("/register")
        public String processRegisterForm(
                @ModelAttribute("customer") CustomerRequest customerRequest,
                Model model) {
            RestTemplate customerService = new RestTemplate();
            CustomerResponse customerResponse = null;
            try {
                customerResponse = customerService.postForObject(
                        "http://customer-service:8081/api/customers", customerRequest,
                        CustomerResponse.class);
            }
            catch (HttpClientErrorException httpError) {
                model.addAttribute("message",
        "Error creating customer " +
                    httpError.getMessage());
                return "register";
            }

            model.addAttribute("customer", customerResponse);
            return "redirect:../rooms";
        }

    }


