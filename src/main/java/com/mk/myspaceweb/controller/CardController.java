package com.mk.myspaceweb.controller;

import com.mk.myspaceweb.data.entity.Card;
import com.mk.myspaceweb.service.CardService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cards")
public class CardController {

    private final CardService cardService;

    private final Logger logger = LoggerFactory.getLogger(CardController.class);

    @GetMapping("/list/{deckId}")
    public String list(@PathVariable int deckId, Model model) {

        model.addAttribute("cards", cardService.getCardsByDeck(deckId));

        return "";
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
