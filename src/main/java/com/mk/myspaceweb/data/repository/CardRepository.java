package com.mk.myspaceweb.data.repository;

import com.mk.myspaceweb.data.entity.Card;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends CrudRepository<Card, Integer> {

    @Query(value = "SELECT * FROM card c WHERE c.deck_id = ?1",
            nativeQuery = true)
    List<Card> getCardsByDeck(int deckId);
}
