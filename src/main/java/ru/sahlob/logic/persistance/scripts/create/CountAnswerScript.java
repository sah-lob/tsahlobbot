package ru.sahlob.logic.persistance.scripts.create;

import org.springframework.stereotype.Component;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.scripts.ScriptMessage;
import ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.*;
import static ru.sahlob.util.Utils.checkTheStringContainsOnlyNumbersBetweenInRange;

@Component
public class CountAnswerScript implements ScriptMessage {

    @Override
    public ScriptNames getName() {
        return ScriptNames.COUNT_ANSWERS;
    }

    @Override
    public String getMessageText(Person person) {
        return COUNT_ANSWERS_TEXT;
    }

    @Override
    public String getButtonText() {
        return ALL_BUTTONS;
    }

    @Override
    public boolean isScriptValid(String message, Person person) {
        return checkTheStringContainsOnlyNumbersBetweenInRange(message, 2, 4);
    }

    @Override
    public void doBackWork(String msg, Person person) {

    }

    @Override
    public List<ScriptNames> getNext(Person person, String message) {
        return Collections.singletonList(ScriptNames.START_QUESTION_PRICE);
    }

    @Override
    public void doWork(String message, Person person) {
        person.getLastGame().setCounters(person.getScriptMessageName(), Integer.parseInt(message));
    }
}
