package ru.sahlob.logic.persistance.game;

import lombok.Data;

@Data
public class Answer {
    private long id;
    private String answerText;
    private boolean rightAnswer;

    public Answer(String answerText, boolean rightAnswer) {
        this.answerText = answerText;
        this.rightAnswer = rightAnswer;
    }
}
