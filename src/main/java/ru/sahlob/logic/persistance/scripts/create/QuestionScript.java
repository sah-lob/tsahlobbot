package ru.sahlob.logic.persistance.scripts.create;

import org.springframework.stereotype.Component;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.game.Game;
import ru.sahlob.logic.persistance.scripts.ScriptMessage;
import ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.*;
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
    public Set<String> additionalButton() {
        return Collections.EMPTY_SET;
    }

    @Override
    public boolean isScriptValid(String message) {
        return true;
    }

    @Override
    public String getErrorValidMessage() {
        return ERROR_VALID_MESSAGE;
    }

    @Override
    public ScriptNames getStepBack() {
        return START;
    }

    @Override
    public List<ScriptNames> getNext(Person person) {
        return Collections.singletonList(GAME_ANSWERS);
    }

    @Override
    public void doWork(String message, Person person) {
        person.getLastGame().getLastTheme().addQuestion(message);
        System.out.println(message);
    }
}
