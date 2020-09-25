package ru.sahlob.logic.persistance.game;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Theme {
    private long id;
    private String themeText;
    private List<Question> questions = new ArrayList<>();

    public Theme(String themeText) {
        this.themeText = themeText;
    }

    public void addQuestion(String question) {
        questions.add(new Question(question));
    }

    public Question getLastQuestion() {
        return questions.get(questions.size() - 1);
    }
}
