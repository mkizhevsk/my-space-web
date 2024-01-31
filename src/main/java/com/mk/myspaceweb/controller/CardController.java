package com.mk.myspaceweb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class CardController {

    @RequestMapping("/")
    public String index(Principal principal) {
        System.out.println("log in: " + LocalDate.now());
        return "redirect:/index";
    }

    @GetMapping("index")
    public String list(Model model) {


        return "index";
    }

}
