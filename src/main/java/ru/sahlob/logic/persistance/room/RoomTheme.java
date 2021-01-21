package ru.sahlob.logic.persistance.room;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import ru.sahlob.logic.persistance.game.Question;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class RoomTheme {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) private Long id;
    private String themeText;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RoomQuestion> roomQuestions = new ArrayList<>();
    private boolean isAllThemeAsked = false;

    public RoomTheme(String themeText) {
        this.themeText = themeText;
    }

    public void addAllQuestions(List<Question> questions) {
        questions.forEach(x ->
                roomQuestions.add(
                        new RoomQuestion(
                                x.getQuestionText(),
                                x.getPrice(),
                                x.getAnswers())));
    }

    public List<RoomQuestion> getRoomQuestions() {
        roomQuestions.removeIf(RoomQuestion::isQuestionAsked);
        if (roomQuestions.size() == 0) {
            isAllThemeAsked = true;
        }
        return roomQuestions;
    }
}
