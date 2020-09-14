package ru.sahlob.logic.persistance.scripts.create;

import org.springframework.stereotype.Component;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.scripts.ScriptMessage;
import ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.*;

@Component
public class ThemeScript implements ScriptMessage {

    @Override
    public ScriptNames getName() {
        return ScriptNames.THEME_GAME;
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
    public ScriptNames getStepBack() {
        return ScriptNames.START;
    }

    @Override
    public Set<ScriptNames> getNext() {
        return new HashSet<>(Arrays.asList(ScriptNames.THEME_GAME, ScriptNames.QUESTION));
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
