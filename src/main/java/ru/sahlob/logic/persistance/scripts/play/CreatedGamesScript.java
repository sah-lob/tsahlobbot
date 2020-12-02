package ru.sahlob.logic.persistance.scripts.play;

import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.game.Game;
import ru.sahlob.logic.persistance.scripts.ScriptMessage;
import ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames;

import java.util.List;
import java.util.Set;

import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.CREATED_GAMES_TEXT;

public class CreatedGamesScript implements ScriptMessage {

    @Override
    public ScriptNames getName() {
        return ScriptNames.CREATED_GAMES;
    }

    @Override
    public String getMessageText(Person person) {
        String messageText = CREATED_GAMES_TEXT + "\n";
        for (Game x : person.getGames()) {
            messageText = messageText + "\n" + "id: " + x.getId() + " name: " + x.getGameName();
        }

        return messageText;
    }

    @Override
    public String getButtonText() {
        return null;
    }

    @Override
    public Set<String> additionalButton() {
        return null;
    }

    @Override
    public boolean isScriptValid(String message) {
        return false;
    }

    @Override
    public String getErrorValidMessage() {
        return null;
    }

    @Override
    public ScriptNames getStepBack() {
        return null;
    }

    @Override
    public List<ScriptNames> getNext(Game game, Person person) {
        return null;
    }

    @Override
    public void doWork(String message, Person person) {

    }
}
