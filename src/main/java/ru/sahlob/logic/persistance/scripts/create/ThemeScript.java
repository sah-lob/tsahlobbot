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

    private int scriptCycle = 0;

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
    public int getScriptCycle() {
        return scriptCycle;
    }

    @Override
    public void setScriptCycle(int scriptCycle) {
        this.scriptCycle = scriptCycle;
    }

    @Override
    public void doWork(String message, Person person) {
        
    }
}
