package ru.sahlob.logic.persistance.scripts.create;

import org.springframework.stereotype.Component;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.game.Game;
import ru.sahlob.logic.persistance.scripts.ScriptMessage;
import ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.*;

@Component
public class GameNameScript implements ScriptMessage {

    @Override
    public ScriptNames getName() {
        return ScriptNames.GAME_NAME;
    }

    @Override
    public String getMessageText(Person person) {
        return GAME_NAME_TEXT;
    }

    @Override
    public String getButtonText() {
        return CREATE_GAME_BUTTON;
    }

    @Override
    public Set<String> additionalButton() {
        return Collections.EMPTY_SET;
    }

    @Override
    public boolean isScriptValid(String message) {
        return true;
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
        return Collections.singletonList(ScriptNames.COUNT_THEMES);
    }

    @Override
    public void doWork(String message, Person person) {
        person.addNewGame(new Game());
        person.getLastGame().setGameName(message);
        System.out.println(message);
    }
}
