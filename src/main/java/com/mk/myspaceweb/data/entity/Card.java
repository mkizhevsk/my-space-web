package com.mk.myspaceweb.data.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "card")
public class Card {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  int id;

    /**
     * Уникальный код для синхронизации
     */
    @Column(name = "internal_code")
    private String internalCode;

    /**
     * Дата и время редактирования
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSZ")
    private LocalDateTime editDateTime;

    /**
     * Лицевая сторона карты
     */
    private String front;

    /**
     * Обратная сторона карты
     */
    private String back;

    /**
     * Пример
     */
    private String example;

    /**
     * Состояние: 0 - не выучено, 1 - выучено
     */
    private int status;
}
