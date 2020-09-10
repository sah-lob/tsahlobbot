package ru.sahlob.logic.persistance.game;

import lombok.Data;

@Data
public class Question {
    private long id;
    private int price;
    private String questionText;
}
