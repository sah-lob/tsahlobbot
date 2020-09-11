package ru.sahlob.logic.persistance.scripts;

import ru.sahlob.logic.persistance.Person;

import java.util.Set;

public interface ScriptMessage {

    String getName();
    String getMessageText();
    String getButtonText();
    String getStepBack();
    Set<String> getNext();
    boolean isCycleExist();
    void doWork(String message, Person person);
}
