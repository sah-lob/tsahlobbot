package ru.sahlob.logic.persistance.scripts.play;

import org.springframework.stereotype.Component;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.scripts.ScriptMessage;
import ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames;

import java.util.*;

import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.*;

@Component
public class OpponentChooseScript implements ScriptMessage {

    @Override
    public ScriptNames getName() {
        return ScriptNames.PLAY;
    }

    @Override
    public String getMessageText(Person person) {
        return PLAY_GAME_TEXT;
    }

    @Override
    public String getButtonText() {
        return PLAY_GAME_BUTTON;
    }

    @Override
    public Set<String> additionalButton() {
        return Collections.emptySet();
    }

    @Override
    public boolean isScriptValid(String message) {
        return message.equals(PLAY_ONE_PLAYER_BUTTON) || message.equals(PLAY_WITH_FRIENDS_BUTTON);
    }

    @Override
    public String getErrorValidMessage() {
        return ERROR_VALID_MESSAGE;
    }

    @Override
    public void doBackWork(String msg, Person person) {
    }

    @Override
    public List<ScriptNames> getNext(Person person, String message) {
        return Collections.singletonList(ScriptNames.PLAY_WITH_FRIENDS);
    }

    @Override
    public void doWork(String message, Person person) {
        System.out.println(message);
    }
}
