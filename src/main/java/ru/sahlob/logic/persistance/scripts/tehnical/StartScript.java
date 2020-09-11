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
    public String getName() {
        return START_NAME;
    }

    @Override
    public String getMessageText() {
        return START_TEXT;
    }

    @Override
    public String getButtonText() {
        return START_BUTTON;
    }

    @Override
    public String getStepBack() {
        return START_BUTTON;
    }

    @Override
    public Set<String> getNext() {
        return new HashSet<>(Arrays.asList(PLAY_GAME_NAME, CREATE_GAME_NAME));
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
