package com.mk.myspaceweb.service;

import com.mk.myspaceweb.data.entity.Card;
import com.mk.myspaceweb.data.repository.CardRepository;
import com.mk.myspaceweb.utils.StringRandomGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    public List<Card> getCards() {
        return cardRepository.getCards();
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
        try {
            cardRepository.deleteById(cardId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
