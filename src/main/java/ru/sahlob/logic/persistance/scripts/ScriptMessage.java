package ru.sahlob.logic.persistance.scripts;

import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.game.Game;
import ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames;

import java.util.List;
import java.util.Set;

public interface ScriptMessage {
    ScriptNames getName();
    String getMessageText(Person person);
    String getButtonText();
    Set<String> additionalButton();
    boolean isScriptValid(String message);
    String getErrorValidMessage();
    ScriptNames getStepBack();
    List<ScriptNames> getNext(Game game, Person person);
    void doWork(String message, Person person);
}
