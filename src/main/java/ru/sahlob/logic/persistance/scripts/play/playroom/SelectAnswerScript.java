package ru.sahlob.logic.persistance.scripts.play.playroom;

import org.springframework.stereotype.Component;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.room.RoomAnswer;
import ru.sahlob.logic.persistance.scripts.ScriptMessage;
import ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.ALL_BUTTONS;
import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames.SELECT_ANSWER;

@Component
public class SelectAnswerScript implements ScriptMessage {

    @Override
    public ScriptNames getName() {
        return SELECT_ANSWER;
    }

    @Override
    public String getMessageText(Person person) {
        var room = person.getRoom();
        var question = room.getSelectedRoomQuestion();
        AtomicReference<String> text = new AtomicReference<>("Вырбан следующий вопрос: \n" + question.getQuestionText() + "\n\n Ответы:");
        question.getAnswers().stream().forEach(x -> {
            text.set(text.get() + "\n " + x.getAnswerText());
        });
        return text.get();
    }

    @Override
    public String getButtonText() {
        return ALL_BUTTONS;
    }

    @Override
    public Set<String> additionalButton(Person person) {
        return person
                .getRoom()
                .getSelectedRoomQuestion()
                .getAnswers()
                .stream()
                .map(RoomAnswer::getAnswerText)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isScriptValid(String message, Person person) {
        return true;
    }

    @Override
    public void doBackWork(String message, Person person) {

    }

    @Override
    public List<ScriptNames> getNext(Person person, String message) {
        return Collections.singletonList(ScriptNames.CHECK_ANSWER);
    }

    @Override
    public void doWork(String message, Person person) {
        var question = person.getRoom().getSelectedRoomQuestion();
        var answer = question
                .getAnswers()
                .stream()
                .filter(x -> x.getAnswerText().equals(message))
                .findFirst()
                .get();

        if (answer.isRightAnswer()) {
            person.setGamePoints(person.getGamePoints() + question.getPrice());
        }
        person.getRoom().incrementNumberOfRespondents();
        System.out.println(message);
    }
}
