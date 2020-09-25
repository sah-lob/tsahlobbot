package ru.sahlob.logic.persistance.scripts.play;

import org.springframework.stereotype.Component;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.game.Game;
import ru.sahlob.logic.persistance.scripts.ScriptMessage;
import ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames;

import java.util.Collections;
import java.util.List;

import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.PLAY_GAME_BUTTON;
import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.PLAY_GAME_TEXT;

@Component
public class PlayGameScript implements ScriptMessage {

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
    public ScriptNames getStepBack() {
        return ScriptNames.START;
    }

    @Override
    public List<ScriptNames> getNext(Game game) {
        return Collections.singletonList(ScriptNames.PLUG);
    }

    @Override
    public void doWork(String message, Person person) {
        System.out.println(message);
    }
}
