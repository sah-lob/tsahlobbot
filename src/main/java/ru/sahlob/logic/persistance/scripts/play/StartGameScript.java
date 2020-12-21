package ru.sahlob.logic.persistance.scripts.play;

import org.springframework.stereotype.Component;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.scripts.ScriptMessage;
import ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.START_GAME;
import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.START_GAME_BUTTON;

@Component
public class StartGameScript implements ScriptMessage {

    @Override
    public ScriptNames getName() {
        return ScriptNames.START_GAME;
    }

    @Override
    public String getMessageText(Person person) {
        return START_GAME;
    }

    @Override
    public String getButtonText() {
        return START_GAME_BUTTON;
    }

    @Override
    public Set<String> additionalButton() {
        return Collections.emptySet();
    }

    @Override
    public boolean isScriptValid(String message, Person person) {
        return true;
    }

    @Override
    public String getErrorValidMessage() {
        return null;
    }

    @Override
    public void doBackWork(String message, Person person) {

    }

    @Override
    public List<ScriptNames> getNext(Person person, String message) {
        return Collections.singletonList(ScriptNames.PLUG);
    }

    @Override
    public void doWork(String message, Person person) {

    }
}
