package ru.sahlob.logic.persistance.scripts.create;

import org.springframework.stereotype.Component;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.scripts.ScriptMessage;
import ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.*;

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
    public void doBackWork(String msg, Person person) {

    }

    @Override
    public List<ScriptNames> getNext(Person person, String message) {
        return Collections.singletonList(ScriptNames.GAME_QUESTIONS);
    }

    @Override
    public void doWork(String message, Person person) {
        person.getLastGame().addTheme(message);
        System.out.println(message);
    }
}
