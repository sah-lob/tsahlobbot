package ru.sahlob.logic.persistance.scripts;

import org.springframework.stereotype.Component;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public interface ScriptMessage {
    ScriptNames getName();
    String getMessageText(Person person);
    String getButtonText();
    default Set<String> additionalButton(Person person) {
        return Collections.emptySet();
    }
    default boolean isScriptValid(String message, Person person) {
     return true;
    }
    default void doBackWork(String message, Person person) { }
    List<ScriptNames> getNext(Person person, String message);
    void doWork(String message, Person person);
    default boolean automaticNextScript(Person person) {
        return false;
    }
    default String getAutomaticMessageText(Person person) {
        return "";
    }
    default void doAutomaticWork(String message, Person person) {

    }
}
