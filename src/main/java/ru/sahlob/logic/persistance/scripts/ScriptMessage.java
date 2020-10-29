package ru.sahlob.logic.persistance.scripts;

import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.game.Game;
import ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames;

import java.util.List;

public interface ScriptMessage {
    ScriptNames getName();
    String getMessageText(Person person);
    String getButtonText();
    boolean isScriptValid(String message);
    String getErrorValidMessage();
    ScriptNames getStepBack();
    List<ScriptNames> getNext(Game game);
    void doWork(String message, Person person);
}
