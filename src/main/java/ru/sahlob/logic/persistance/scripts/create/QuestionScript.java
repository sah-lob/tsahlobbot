package ru.sahlob.logic.persistance.scripts.create;

import org.springframework.stereotype.Component;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.game.Game;
import ru.sahlob.logic.persistance.scripts.ScriptMessage;
import ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames;

import java.util.Collections;
import java.util.List;

import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.ALL_BUTTONS;
import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.QUESTION_TEXT;
import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames.GAME_ANSWERS;
import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames.START;

@Component
public class QuestionScript implements ScriptMessage {

    @Override
    public ScriptNames getName() {
        return ScriptNames.GAME_QUESTIONS;
    }

    @Override
    public String getMessageText(Person person) {
        return QUESTION_TEXT + " " + (person
                                              .getLastGame().getLastTheme()
                                              .getQuestions()
                                              .size() + 1);
    }

    @Override
    public String getButtonText() {
        return ALL_BUTTONS;
    }

    @Override
    public ScriptNames getStepBack() {
        return START;
    }

    @Override
    public List<ScriptNames> getNext(Game game) {
        return Collections.singletonList(GAME_ANSWERS);
    }

    @Override
    public void doWork(String message, Person person) {
        person.getLastGame().getLastTheme().addQuestion(message);
        System.out.println(message);
    }
}
