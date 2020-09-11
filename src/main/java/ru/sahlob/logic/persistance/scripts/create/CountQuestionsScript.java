package ru.sahlob.logic.persistance.scripts.create;

import org.springframework.stereotype.Component;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.scripts.ScriptMessage;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.*;

@Component
public class CountQuestionsScript implements ScriptMessage {

    @Override
    public String getName() {
        return COUNT_QUESTIONS_NAME;
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
    public String getStepBack() {
        return COUNT_THEME_NAME;
    }

    @Override
    public Set<String> getNext() {
        return new HashSet<>(Collections.singletonList(PLUG_NAME));
    }

    @Override
    public boolean isCycleExist() {
        return false;
    }


    @Override
    public void doWork(String message, Person person) {
    }
}
