package ru.sahlob.logic.persistance.scripts.play;

import org.springframework.stereotype.Component;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.scripts.ScriptMessage;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.*;

@Component
public class PlayGameScript implements ScriptMessage {

    @Override
    public String getName() {
        return PLAY_GAME_NAME;
    }

    @Override
    public String getMessageText() {
        return PLAY_GAME_TEXT;
    }

    @Override
    public String getButtonText() {
        return PLAY_GAME_BUTTON;
    }

    @Override
    public String getStepBack() {
        return START_BUTTON;
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
        System.out.println(message);
    }
}
