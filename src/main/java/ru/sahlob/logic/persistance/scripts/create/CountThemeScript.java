package ru.sahlob.logic.persistance.scripts.create;

import org.springframework.stereotype.Component;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.game.Game;
import ru.sahlob.logic.persistance.scripts.ScriptMessage;
import ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames;

import java.util.Collections;
import java.util.List;

import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.COUNT_THEME_GAME_TEXT;
import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.CREATE_GAME_BUTTON;

@Component
public class CountThemeScript implements ScriptMessage {

    @Override
    public ScriptNames getName() {
        return ScriptNames.COUNT_THEMES;
    }

    @Override
    public String getMessageText(Person person) {
        return COUNT_THEME_GAME_TEXT;
    }

    @Override
    public String getButtonText() {
        return CREATE_GAME_BUTTON;
    }

    @Override
    public ScriptNames getStepBack() {
        return ScriptNames.START;
    }

    @Override
    public List<ScriptNames> getNext(Game game) {
        return Collections.singletonList(ScriptNames.COUNT_QUESTIONS);
    }

    @Override
    public void doWork(String message, Person person) {
        person.addNewGame(new Game());
        person.getLastGame().setCounters(person.getScriptMessage().getName(), Integer.parseInt(message));
        System.out.println(message);
    }
}
