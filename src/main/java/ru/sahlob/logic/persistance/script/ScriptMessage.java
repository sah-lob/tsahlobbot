package ru.sahlob.logic.persistance.script;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ScriptMessage {
    private int id;
    private String messageText;
    private String buttonText;
    private String stepBack;
    private Set<String> next = new HashSet<>();

    public ScriptMessage(String messageText, String buttonText, String stepBack, Set<String> next) {
        this.messageText = messageText;
        this.buttonText = buttonText;
        this.stepBack = stepBack;
        this.next = next;
    }

    public boolean nextTextExist(String messageText) {
        return next.contains(messageText);
    }
}
