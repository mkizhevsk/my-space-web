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

        model.addAttribute("deck", cardService.getDeck(deckId));
        model.addAttribute("cards", cardService.getCardsByDeck(deckId));

        return "card/card-list";
    }

    @GetMapping("/addCard/{deckId}")
    public String addCard(@PathVariable int deckId, Model model) {

        model.addAttribute("deck", cardService.getDeck(deckId));
        model.addAttribute("card", new Card(0));

        return "card/card-form";
    }

    @GetMapping("/updateCard/{deckId}/{cardId}")
    public String updateCustomer(@PathVariable int deckId, @PathVariable int cardId, Model model) {

        var card = cardService.getCard(cardId);
        model.addAttribute("card", card);

        return "card/card-form";
    }

    @PostMapping("/saveCard/{deckId}")
    public String saveCard(@PathVariable int deckId, @ModelAttribute Card card) {

        cardService.saveCard(card);

        return "redirect:/index";
    }

    @GetMapping("/deleteCard/{deckId}/{cardId}")
    public String deleteContact(@PathVariable int deckId, @PathVariable int cardId) {

        cardService.deleteCard(cardId);

        return "redirect:/index";
    }

}
