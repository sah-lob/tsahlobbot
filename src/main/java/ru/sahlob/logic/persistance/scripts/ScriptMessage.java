package ru.sahlob.logic.persistance.scripts;

import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames;

import java.util.List;
import java.util.Set;

public interface ScriptMessage {
    ScriptNames getName();
    String getMessageText(Person person);
    String getButtonText();
    Set<String> additionalButton();
    boolean isScriptValid(String message, Person person);
    String getErrorValidMessage();
    void doBackWork(String message, Person person);
    List<ScriptNames> getNext(Person person, String message);
    void doWork(String message, Person person);
}
