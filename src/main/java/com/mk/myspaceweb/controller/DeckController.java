package com.mk.myspaceweb.controller;

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
public class DeckController {

    private final CardService cardService;

    @RequestMapping("/")
    public String index() {
        System.out.println("log in: " + LocalDate.now());
        return "redirect:/index";
    }

    @GetMapping("index")
    public String list(Model model, Principal principal) {

        String username = principal.getName();
        model.addAttribute("decks", cardService.getDecksByUser(username));

        return "index";
    }
}
