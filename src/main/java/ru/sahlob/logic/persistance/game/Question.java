package ru.sahlob.logic.persistance.game;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Question {
    private long id;
    private int price;
    private String questionText;
    private List<Answer> answers = new ArrayList<>();

    public Question(String questionText) {
        this.questionText = questionText;
    }

    public void addAnswer(String answer) {
        answers.add(new Answer(answer, answers.isEmpty()));
    }
}
