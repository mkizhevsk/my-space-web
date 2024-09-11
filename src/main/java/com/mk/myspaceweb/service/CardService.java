package com.mk.myspaceweb.service;

import com.mk.myspaceweb.data.dto.CardDto;
import com.mk.myspaceweb.data.dto.DeckDto;
import com.mk.myspaceweb.data.entity.Card;
import com.mk.myspaceweb.data.entity.Deck;
import com.mk.myspaceweb.data.repository.CardRepository;
import com.mk.myspaceweb.data.repository.DeckRepository;
import com.mk.myspaceweb.data.repository.UserRepository;
import com.mk.myspaceweb.utils.StringRandomGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardService {

    private final DeckRepository deckRepository;
    private final CardRepository cardRepository;
    private final UserRepository userRepository;

    public Deck getDeck(int deckId) {
        return deckRepository.findById(deckId).orElse(null);
    }

    public List<Deck> getDecksByUser(String username) {
        return deckRepository.getDecksByUser(username);
    }

    public void saveDeck(DeckDto deckDto, String username) {
        Deck deck;
        if (deckDto.getDeckId() == 0) {
            deck = Deck.builder()
                    .internalCode(StringRandomGenerator.getInstance().getValue())
                    .user(userRepository.findByUsername(username))
                    .build();
        } else {
            deck = getDeck(deckDto.getDeckId());
        }
        deck.setName(deckDto.getName());
        deck.setEditDateTime(LocalDateTime.now());

        deckRepository.save(deck);
    }

    public List<Card> getCardsByDeck(int deckId) {
        var cards = cardRepository.getCardsByDeck(deckId);
        return cards.stream()
                .filter(card -> !card.isDeleted())
                .collect(Collectors.toList());
    }

    public void saveCard(CardDto cardDto, int deckId) {
        Card card;
        if (cardDto.getCardId() == 0) {
            card = Card.builder()
                    .internalCode(StringRandomGenerator.getInstance().getValue())
                    .build();
        } else {
            card = getCard(cardDto.getCardId());
        }
        card.setFront(cardDto.getFront());
        card.setBack(cardDto.getBack());
        card.setExample(cardDto.getExample());
        card.setStatus(cardDto.getStatus());
        card.setEditDateTime(LocalDateTime.now());
        cardRepository.save(card);

        var deck = getDeck(deckId);
        deck.addCard(card);
        deckRepository.save(deck);
    }

    public Card getCard(int cardId) {
        return cardRepository.findById(cardId).orElse(null);
    }

    public void deleteCard(int cardId) {
        var card = getCard(cardId);
        card.setDeleted(true);
        cardRepository.save(card);
    }

    public CardDto getCardDto(int cardId) {
        var card = getCard(cardId);

        return CardDto.builder()
                .cardId(cardId)
                .front(card.getFront())
                .back(card.getBack())
                .example(card.getExample())
                .status(card.getStatus())
                .build();
    }
}
