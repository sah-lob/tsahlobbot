package ru.sahlob.logic.persistance.scripts;

import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames;

import java.util.Set;

public interface ScriptMessage {

    ScriptNames getName();
    String getMessageText();
    String getButtonText();
    ScriptNames getStepBack();
    Set<ScriptNames> getNext();
    boolean isCycleExist();
    void doWork(String message, Person person);
}
