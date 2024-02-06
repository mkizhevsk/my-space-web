package com.mk.myspaceweb.controller;

import com.mk.myspaceweb.data.entity.Card;
import com.mk.myspaceweb.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/updateCard/{cardId}")
    public String updateCustomer(@PathVariable int cardId, Model model) {

        var card = cardService.getCard(cardId);
        model.addAttribute("card", card);

        return "card-form";
    }

    @PostMapping("/saveCard")
    public String saveCard(@ModelAttribute Card card) {

        cardService.saveCard(card);

        return "redirect:/index";
    }

    @GetMapping("/deleteCard/{cardId}")
    public String deleteContact(@PathVariable int cardId) {

        cardService.deleteCard(cardId);

        return "redirect:/index";
    }

}
