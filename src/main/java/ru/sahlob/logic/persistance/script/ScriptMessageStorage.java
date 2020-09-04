package ru.sahlob.logic.persistance.script;

import org.springframework.stereotype.Service;

import java.util.*;

import static ru.sahlob.logic.persistance.script.ScriptMessageText.*;

@Service
public class ScriptMessageStorage {

    private final HashMap<String, ScriptMessage> scriptMessages = new HashMap<>();

    public ScriptMessageStorage() {
        putNewMessage(PLUG_GAME_BUTTON, PLUG_GAME_TEXT, START_BUTTON, Collections.singletonList(START_BUTTON));
        putNewMessage(CREATE_GAME_BUTTON, CREATE_GAME_TEXT, START_BUTTON, Collections.singletonList(PLUG_GAME_BUTTON));
        putNewMessage(PLAY_GAME_BUTTON, PLAY_GAME_TEXT, START_BUTTON, Collections.singletonList(PLUG_GAME_BUTTON));
        putNewMessage(START_BUTTON, START_TEXT, START_BUTTON, Arrays.asList(PLAY_GAME_BUTTON, CREATE_GAME_BUTTON));
    }

    public ScriptMessage get() {
        return scriptMessages.get(START_BUTTON);
    }

    public ScriptMessage updateScript(ScriptMessage scriptMessage, String text) {
        if (text.equals(BACK_BUTTON)) {
            return scriptMessages.get(scriptMessage.getStepBack());
        }
        if (scriptMessage.nextTextExist(text)) {
            return scriptMessages.get(text);
        } else {
            return scriptMessages.get(START_BUTTON);
        }
    }

    private void putNewMessage(String button, String text, String stepBack, List<String> nextSteps) {
        scriptMessages.put(button,
                new ScriptMessage(text,
                        button,
                        stepBack,
                        new HashSet<>(nextSteps)));
    }
}
