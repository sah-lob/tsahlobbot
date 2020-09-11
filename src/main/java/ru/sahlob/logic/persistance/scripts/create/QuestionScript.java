package ru.sahlob.logic.persistance.scripts.create;

import org.springframework.stereotype.Component;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.scripts.ScriptMessage;

import java.util.Collections;
import java.util.Set;

import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.*;

@Component
public class QuestionScript implements ScriptMessage {

    @Override
    public String getName() {
        return QUESTION_NAME;
    }

    @Override
    public String getMessageText() {
        return QUESTION_TEXT;
    }

    @Override
    public String getButtonText() {
        return ALL_BUTTONS;
    }

    @Override
    public String getStepBack() {
        return START_NAME;
    }

    @Override
    public Set<String> getNext() {
        return Collections.singleton(PLUG_NAME);
    }

    @Override
    public boolean isCycleExist() {
        return false;
    }

    @Override
    public void doWork(String message, Person person) {
        System.out.println(message);
    }
}
