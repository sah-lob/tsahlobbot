package ru.sahlob.logic.persistance.scripts.tehnical;

import org.springframework.stereotype.Component;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.game.Game;
import ru.sahlob.logic.persistance.scripts.ScriptMessage;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
    public Set<String> additionalButton() {
        return Collections.EMPTY_SET;
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
    public List<ScriptNames> getNext(Game game, Person person) {
        var list =  Arrays.asList(ScriptNames.PLAY, ScriptNames.COUNT_THEMES);
        if (!person.getGames().isEmpty()) {
            list.add(ScriptNames.CREATED_GAMES);
        }
        return list;
    }

    @Override
    public void doWork(String message, Person person) {
        System.out.println(message);
    }
}
