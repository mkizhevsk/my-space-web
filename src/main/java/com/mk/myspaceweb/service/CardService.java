package com.mk.myspaceweb.service;

import com.mk.myspaceweb.data.entity.Card;
import com.mk.myspaceweb.data.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    public List<Card> getCards() {
        return cardRepository.getCards();
    }
}
