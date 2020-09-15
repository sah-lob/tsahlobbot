package ru.sahlob.logic.persistance.scripts.tehnical;

import org.springframework.stereotype.Component;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.scripts.ScriptMessage;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.*;

@Component
public class StartScript implements ScriptMessage {

    @Override
    public ScriptNames getName() {
        return ScriptNames.START;
    }

    @Override
    public String getMessageText(Person person) {
        return START_TEXT;
    }

    @Override
    public String getButtonText() {
        return START_BUTTON;
    }

    @Override
    public ScriptNames getStepBack() {
        return ScriptNames.START;
    }

    @Override
    public Set<ScriptNames> getNext() {
        return new HashSet<>(Arrays.asList(ScriptNames.PLAY, ScriptNames.COUNT_THEME));
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
