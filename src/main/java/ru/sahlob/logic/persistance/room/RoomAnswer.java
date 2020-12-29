package ru.sahlob.logic.persistance.room;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@RequiredArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class RoomAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) private Long id;
    private String answerText;
    private boolean rightAnswer;

    public RoomAnswer(String answerText, boolean rightAnswer) {
        this.answerText = answerText;
        this.rightAnswer = rightAnswer;
    }
}
