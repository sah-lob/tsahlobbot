package ru.sahlob.logic.persistance.script;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ScriptMessage {
    private int id;
    private String name;
    private String messageText;
    private String buttonText;
    private String stepBack;
    private Set<String> next = new HashSet<>();

    public ScriptMessage(String name, String messageText, String buttonText, String stepBack, Set<String> next) {
        this.name = name;
        this.messageText = messageText;
        this.buttonText = buttonText;
        this.stepBack = stepBack;
        this.next = next;
    }
}
