package ru.sahlob.logic.persistance.game;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) private Long id;
    private int price;
    private String questionText;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Answer> answers = new ArrayList<>();

    public Question(String questionText, int price) {
        this.questionText = questionText;
        this.price = price;
    }

    public void addAnswer(String answer) {
        answers.add(new Answer(answer, answers.isEmpty()));
    }
}
