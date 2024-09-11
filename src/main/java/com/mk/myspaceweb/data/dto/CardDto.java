package com.mk.myspaceweb.data.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CardDto {

    private int cardId;
    private String front;
    private String back;
    private String example;
    private int status;

    public CardDto(int cardId) {
        this.cardId = cardId;
    }
}
