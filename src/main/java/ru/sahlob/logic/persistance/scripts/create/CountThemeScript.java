package ru.sahlob.logic.persistance.scripts.create;

import org.springframework.stereotype.Component;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.game.Game;
import ru.sahlob.logic.persistance.scripts.ScriptMessage;
import ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.*;

@Component
public class CountThemeScript implements ScriptMessage {

    @Override
    public ScriptNames getName() {
        return ScriptNames.COUNT_THEME;
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
    public Set<ScriptNames> getNext() {
        return new HashSet<>(Collections.singletonList(ScriptNames.COUNT_QUESTIONS));
    }

    @Override
    public boolean isCycleExist() {
        return false;
    }


    @Override
    public void doWork(String message, Person person) {
        person.addNewGame(new Game());
        person.getLastGame().setThemeCount(Integer.parseInt(message));
        System.out.println(message);
    }
}
