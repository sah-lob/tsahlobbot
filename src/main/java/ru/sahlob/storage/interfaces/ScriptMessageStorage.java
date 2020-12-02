package ru.sahlob.storage.interfaces;

import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.scripts.ScriptMessage;
import ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames;

import java.util.Set;

public interface ScriptMessageStorage {
    ScriptMessage getStartMessage();
    ScriptMessage updateScript(Person person, String text);
    Set<String> getNextButtons(ScriptMessage scriptMessage, Person person);
    ScriptMessage getScriptMessage(ScriptNames scriptName);
    ScriptMessage getScriptMessage(Person person);
}
