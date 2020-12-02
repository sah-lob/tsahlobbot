package ru.sahlob.logic.persistance.scripts.play;

import org.springframework.stereotype.Component;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.game.Game;
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
        return new HashSet<>(Arrays.asList(PLAY_ONE_PLAYER_BUTTON, PLAY_WITH_FRIENDS_BUTTON));
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
    public ScriptNames getStepBack() {
        return ScriptNames.START;
    }

    @Override
    public List<ScriptNames> getNext(Person person) {
        return Collections.singletonList(ScriptNames.PLUG);
    }

    @Override
    public void doWork(String message, Person person) {
        System.out.println(message);
    }
}
