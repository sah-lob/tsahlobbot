package ru.sahlob.logic.persistance.scripts.tehnical;

import org.springframework.stereotype.Component;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.scripts.ScriptMessage;

import java.util.*;

import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.*;
import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames.*;

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
    public boolean isScriptValid(String message, Person person) {
        return message.equals(CREATE_GAME_BUTTON)
               || message.equals(PLAY_GAME_BUTTON)
               || message.equals(CREATED_GAMES_BUTTON)
               || message.equals(PLAYER_ID_BUTTON);
    }

    @Override
    public void doBackWork(String msg, Person person) { }

    @Override
    public List<ScriptNames> getNext(Person person, String message) {
        var list = new ArrayList<>(Arrays.asList(PLAY, GAME_NAME, PLAYER_ID));
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
