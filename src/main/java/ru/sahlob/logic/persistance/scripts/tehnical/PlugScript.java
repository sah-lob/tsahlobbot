package ru.sahlob.logic.persistance.scripts.tehnical;

import org.springframework.stereotype.Component;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.scripts.ScriptMessage;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.*;

@Component
public class PlugScript implements ScriptMessage {

    @Override
    public ScriptNames getName() {
        return ScriptNames.PLUG;
    }

    @Override
    public String getMessageText(Person person) {
        return PLUG_GAME_TEXT;
    }

    @Override
    public String getButtonText() {
        return PLUG_GAME_BUTTON;
    }

    @Override
    public ScriptNames getStepBack() {
        return ScriptNames.START;
    }

    @Override
    public Set<ScriptNames> getNext() {
        return new HashSet<>(Collections.singletonList(ScriptNames.START));
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
