package com.mk.myspaceweb.data.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DeckDto {

    private int deckId;
    private String name;

    public DeckDto(int deckId) {
        this.deckId = deckId;
    }
}
