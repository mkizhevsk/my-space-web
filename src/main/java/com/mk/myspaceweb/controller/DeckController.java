package com.mk.myspaceweb.controller;

import com.mk.myspaceweb.data.dto.DeckDto;
import com.mk.myspaceweb.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        System.out.println("index " + principal.getName());
        String username = principal.getName();
        model.addAttribute("decks", cardService.getDecksByUser(username));

        return "index";
    }

    @GetMapping("/addDeck")
    public String addDeck(Model model) {

        model.addAttribute("deckDto", new DeckDto(0));

        return "deck-form";
    }

    @GetMapping("/updateDeck/{deckId}")
    public String updateDeck(@PathVariable int deckId, Model model) {

        var deck = cardService.getDeck(deckId);
        model.addAttribute("deckDto", DeckDto.builder()
                .name(deck.getName())
                .deckId(deckId)
                .build());

        return "deck-form";
    }

    @PostMapping("/saveDeck")
    public String saveDeck(@ModelAttribute DeckDto deckDto, Principal principal) {

        cardService.saveDeck(deckDto, principal.getName());

        return "redirect:/index";
    }

    @GetMapping("/deleteDeck/{deckId}")
    public String deleteContact(@PathVariable int deckId) {

        cardService.deleteDeck(deckId);

        return "redirect:/index";
    }
}
