package ru.sahlob.logic.persistance.scripts.tehnical;

import org.springframework.stereotype.Component;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.game.Game;
import ru.sahlob.logic.persistance.scripts.ScriptMessage;

import java.util.Arrays;
import java.util.List;

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
    public boolean isScriptValid(String message) {
        return message.equals(CREATE_GAME_BUTTON) || message.equals(PLAY_GAME_BUTTON);
    }

    @Override
    public String getErrorValidMessage() {
        return null;
    }

    @Override
    public ScriptNames getStepBack() {
        return ScriptNames.START;
    }

    @Override
    public List<ScriptNames> getNext(Game game) {
        return Arrays.asList(ScriptNames.PLAY, ScriptNames.COUNT_THEMES);
    }

    @Override
    public void doWork(String message, Person person) {
        System.out.println(message);
    }
}
