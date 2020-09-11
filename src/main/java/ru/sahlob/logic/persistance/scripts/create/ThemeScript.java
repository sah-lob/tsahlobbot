package ru.sahlob.logic.persistance.scripts.create;

import org.springframework.stereotype.Component;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.scripts.ScriptMessage;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.*;

@Component
public class ThemeScript implements ScriptMessage {

    @Override
    public String getName() {
        return THEME_GAME_NAME;
    }

    @Override
    public String getMessageText() {
        return THEME_GAME_TEXT;
    }

    @Override
    public String getButtonText() {
        return ALL_BUTTONS;
    }

    @Override
    public String getStepBack() {
        return null;
    }

    @Override
    public Set<String> getNext() {
        return new HashSet<>(Arrays.asList(THEME_GAME_NAME, QUESTION_NAME));
    }

    @Override
    public boolean isCycleExist() {
        return true;
    }

    @Override
    public void doWork(String message, Person person) {
        System.out.println(message);
    }
}
