package com.mk.myspaceweb.controller;

import com.mk.myspaceweb.data.entity.Card;
import com.mk.myspaceweb.service.CardService;
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

    private final CardService cardService;

    @RequestMapping("/")
    public String index(Principal principal) {
        System.out.println("log in: " + LocalDate.now());
        return "redirect:/index";
    }

    @GetMapping("index")
    public String list(Model model) {

        model.addAttribute("cards", cardService.getCards());

        return "index";
    }

    @GetMapping("/addCard")
    public String addCard(Model model) {

        model.addAttribute("card", new Card(0));

        return "card-form";
    }

}
