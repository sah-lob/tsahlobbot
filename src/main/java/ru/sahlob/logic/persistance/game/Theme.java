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
public class Theme {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) private Long id;
    private String themeText;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Question> questions = new ArrayList<>();

    public Theme(String themeText) {
        this.themeText = themeText;
    }

    public void addQuestion(String question, int questionStartPrice, int questionPriceStep) {
        int price = questions.isEmpty() ? questionStartPrice : questions.size() * questionPriceStep + questionStartPrice;
        questions.add(new Question(question, price));
    }

    public Question getLastQuestion() {
        return questions.get(questions.size() - 1);
    }
}
