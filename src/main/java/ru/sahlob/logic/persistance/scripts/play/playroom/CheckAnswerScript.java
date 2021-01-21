package ru.sahlob.logic.persistance.scripts.play.playroom;

import lombok.Data;
import org.springframework.stereotype.Component;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.room.RoomAnswer;
import ru.sahlob.logic.persistance.scripts.ScriptMessage;
import ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames;

import java.util.Collections;
import java.util.List;

import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.ALL_BUTTONS;
import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames.*;

@Component
@Data
public class CheckAnswerScript implements ScriptMessage {

    @Override
    public ScriptNames getName() {
        return CHECK_ANSWER;
    }

    @Override
    public String getMessageText(Person person) {
        String text = "Правильный ответ:\n " + person
                .getRoom()
                .getSelectedRoomQuestion()
                .getAnswers()
                .stream()
                .filter(RoomAnswer::isRightAnswer)
                .map(RoomAnswer::getAnswerText)
                .findFirst()
                .get();
        text += ".\nВсего у вас " + person.getGamePoints() + " очков";
        return text;
    }

    @Override
    public String getButtonText() {
        return ALL_BUTTONS;
    }

    @Override
    public List<ScriptNames> getNext(Person person, String message) {
        return Collections.singletonList(ScriptNames.PLUG);
    }

    @Override
    public void doWork(String message, Person person) {
        System.out.println(message);
    }

    @Override
    public boolean automaticNextScript(Person person) {
        return true;
    }

    @Override
    public String getAutomaticMessageText(Person person) {
        String text = "Правильный ответ:\n " + person
                .getRoom()
                .getSelectedRoomQuestion()
                .getAnswers()
                .stream()
                .filter(RoomAnswer::isRightAnswer)
                .map(RoomAnswer::getAnswerText)
                .findFirst()
                .get();
        text += ".\nВсего у вас " + person.getGamePoints() + " очков";
        return text;
    }

    @Override
    public void doAutomaticWork(String message, Person person) {
        if (person.getRoom().getNumberOfRespondents() >= person.getRoom().getPlayers().size()) {
            person.getRoom().incrementNextChoosePersonId();
            var question = person.getRoom().getSelectedRoomQuestion();
            question.setQuestionAsked(true);
            var nextPersonId = person.getRoom().getNextChoosePersonId();
            if (person.getId() == nextPersonId) {
                person.setScriptMessageName(ROOM);

            } else {
                // сделаем позже.
            }
        }
    }
}
