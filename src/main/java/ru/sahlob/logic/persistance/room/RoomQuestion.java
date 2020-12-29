package ru.sahlob.logic.persistance.room;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import ru.sahlob.logic.persistance.game.Answer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class RoomQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) private Long id;
    private int price;
    private String questionText;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RoomAnswer> answers = new ArrayList<>();
    private boolean isQuestionAsked = false;

    public RoomQuestion(String questionText, int price, List<Answer> answers) {
        this.questionText = questionText;
        this.price = price;
        addAllAnswers(answers);
    }

    public void addAllAnswers(List<Answer> answers) {
        answers.forEach(x -> {
            var roomAnswer = new RoomAnswer();
            roomAnswer.setAnswerText(x.getAnswerText());
            roomAnswer.setRightAnswer(x.isRightAnswer());
            this.answers.add(roomAnswer);
        });
    }
}
