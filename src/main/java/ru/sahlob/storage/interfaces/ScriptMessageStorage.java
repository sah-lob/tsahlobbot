package ru.sahlob.storage.interfaces;

import ru.sahlob.logic.persistance.scripts.ScriptMessage;

import java.util.Set;

public interface ScriptMessageStorage {
    ScriptMessage getStartMessage();
    ScriptMessage updateScript(ScriptMessage scriptMessage, String text);
    Set<String> getNextButtons(ScriptMessage scriptMessage);
}
