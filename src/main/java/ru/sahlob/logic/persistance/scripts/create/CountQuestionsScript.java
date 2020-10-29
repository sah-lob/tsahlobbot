package ru.sahlob.logic.persistance.scripts.create;

import org.springframework.stereotype.Component;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.game.Game;
import ru.sahlob.logic.persistance.scripts.ScriptMessage;
import ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames;

import java.util.Collections;
import java.util.List;

import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.*;
import static ru.sahlob.util.Utils.checkTheStringContainsOnlyNumbers;

@Component
public class CountQuestionsScript implements ScriptMessage {

    @Override
    public ScriptNames getName() {
        return ScriptNames.COUNT_QUESTIONS;
    }

    @Override
    public String getMessageText(Person person) {
        return COUNT_QUESTIONS_TEXT;
    }

    @Override
    public String getButtonText() {
        return ALL_BUTTONS;
    }

    @Override
    public boolean isScriptValid(String message) {
        return checkTheStringContainsOnlyNumbers(message);
    }

    @Override
    public String getErrorValidMessage() {
        return ERROR_VALID_MESSAGE;
    }

    @Override
    public ScriptNames getStepBack() {
        return ScriptNames.COUNT_THEMES;
    }

    @Override
    public List<ScriptNames> getNext(Game game) {
        return Collections.singletonList(ScriptNames.COUNT_ANSWERS);
    }

    @Override
    public void doWork(String message, Person person) {
        person.getLastGame().setCounters(person.getScriptMessage().getName(), Integer.parseInt(message));
        System.out.println(message);
    }
}
