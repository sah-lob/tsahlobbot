package ru.sahlob.logic.persistance.scripts.create;

import org.springframework.stereotype.Component;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.scripts.ScriptMessage;
import ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.*;

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
    public ScriptNames getStepBack() {
        return ScriptNames.COUNT_QUESTIONS;
    }

    @Override
    public Set<ScriptNames> getNext() {
        return new HashSet<>(Collections.singletonList(ScriptNames.THEME_GAME));
    }

    @Override
    public boolean isCycleExist() {
        return false;
    }

    @Override
    public void doWork(String message, Person person) {
        person.getLastGame().setAnswerCount(Integer.parseInt(message));
    }
}
