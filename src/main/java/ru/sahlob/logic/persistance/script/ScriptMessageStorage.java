package ru.sahlob.logic.persistance.script;

import java.util.Set;

public interface ScriptMessageStorage {
    ScriptMessage getStartMessage();
    ScriptMessage updateScript(ScriptMessage scriptMessage, String text);
    Set<String> getNextButtons(ScriptMessage scriptMessage);
}
