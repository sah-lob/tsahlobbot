package ru.sahlob.logic.persistance.scripts.tehnical;

import org.springframework.stereotype.Component;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.game.Game;
import ru.sahlob.logic.persistance.scripts.ScriptMessage;

import java.util.Arrays;
import java.util.List;

import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.START_BUTTON;
import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.START_TEXT;

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
