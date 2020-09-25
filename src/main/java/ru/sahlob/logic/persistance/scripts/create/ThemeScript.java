package ru.sahlob.logic.persistance.scripts.create;

import org.springframework.stereotype.Component;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.game.Game;
import ru.sahlob.logic.persistance.scripts.ScriptMessage;
import ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames;

import java.util.Collections;
import java.util.List;

import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.ALL_BUTTONS;
import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.THEME_GAME_TEXT;

@Component
public class ThemeScript implements ScriptMessage {

    @Override
    public ScriptNames getName() {
        return ScriptNames.GAME_THEMES;
    }

    @Override
    public String getMessageText(Person person) {
        return THEME_GAME_TEXT + " " + (person.getLastGame().getThemes().size() + 1);
    }

    @Override
    public String getButtonText() {
        return ALL_BUTTONS;
    }

    @Override
    public ScriptNames getStepBack() {
        return ScriptNames.START;
    }

    @Override
    public List<ScriptNames> getNext(Game game) {
        return Collections.singletonList(ScriptNames.GAME_QUESTIONS);
    }

    @Override
    public void doWork(String message, Person person) {
        person.getLastGame().addTheme(message);
        System.out.println(message);
    }
}
