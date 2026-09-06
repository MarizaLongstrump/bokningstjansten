package com.mariza.bokning.controller.web;

import com.mariza.bokning.dto.Customer.CustomerRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



    @Controller
    @RequestMapping("/customer")
    public class CustomerWebController {

        @GetMapping("/register")
        public String showRegisterForm(Model model) {
            model.addAttribute("customer", new CustomerRequest());
            return "register";
        }
    }


