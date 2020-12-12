package ru.sahlob.logic.persistance.scripts.play;

import org.springframework.stereotype.Component;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.game.Game;
import ru.sahlob.logic.persistance.scripts.ScriptMessage;
import ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.CREATED_GAMES_BUTTON;
import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.CREATED_GAMES_TEXT;

@Component
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
        return CREATED_GAMES_BUTTON;
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
        return null;
    }

    @Override
    public void doBackWork(String msg, Person person) { }

    @Override
    public List<ScriptNames> getNext(Person person, String message) {
        return Collections.singletonList(ScriptNames.START);
    }

    @Override
    public void doWork(String message, Person person) {
        System.out.println(message);
    }
}
