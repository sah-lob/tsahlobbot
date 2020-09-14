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
public class CountQuestionsScript implements ScriptMessage {

    @Override
    public ScriptNames getName() {
        return ScriptNames.COUNT_QUESTIONS;
    }

    @Override
    public String getMessageText() {
        return COUNT_QUESTIONS_TEXT;
    }

    @Override
    public String getButtonText() {
        return ALL_BUTTONS;
    }

    @Override
    public ScriptNames getStepBack() {
        return ScriptNames.COUNT_THEME;
    }

    @Override
    public Set<ScriptNames> getNext() {
        return new HashSet<>(Collections.singletonList(ScriptNames.COUNT_ANSWERS));
    }

    @Override
    public boolean isCycleExist() {
        return false;
    }


    @Override
    public void doWork(String message, Person person) {
        person.getLastGame().setQuestionCount(Integer.parseInt(message));
        System.out.println(message);
    }
}
