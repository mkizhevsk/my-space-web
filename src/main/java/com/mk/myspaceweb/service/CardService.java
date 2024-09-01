package com.mk.myspaceweb.service;

import com.mk.myspaceweb.data.entity.Card;
import com.mk.myspaceweb.data.entity.Deck;
import com.mk.myspaceweb.data.repository.CardRepository;
import com.mk.myspaceweb.data.repository.DeckRepository;
import com.mk.myspaceweb.utils.StringRandomGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardService {

    private final DeckRepository deckRepository;
    private final CardRepository cardRepository;

    public Object getDeck(int deckId) {
        return deckRepository.findById(deckId).orElse(null);
    }

    public List<Deck> getDecksByUser(String username) {
        return deckRepository.getDecksByUser(username);
    }

    public List<Card> getCardsByDeck(int deckId) {
        var cards = cardRepository.getCardsByDeck(deckId);
        return cards.stream()
                .filter(card -> !card.isDeleted())
                .collect(Collectors.toList());
    }

    public void saveCard(Card card) {

        if(ObjectUtils.isEmpty(card.getInternalCode()))
            card.setInternalCode(StringRandomGenerator.getInstance().getValue());

        card.setEditDateTime(LocalDateTime.now());

        cardRepository.save(card);
    }

    public Card getCard(int cardId) {
        return cardRepository.findById(cardId).orElse(null);
    }

    public void deleteCard(int cardId) {
        var card = getCard(cardId);
        card.setDeleted(true);
        saveCard(card);
    }


}
