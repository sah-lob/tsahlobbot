package ru.sahlob.logic.persistance.script;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static ru.sahlob.logic.persistance.script.ScriptMessageText.*;

@Service
public class ScriptMessageMemoryStorage implements  ScriptMessageStorage {

    private final HashMap<String, ScriptMessage> scriptMessages = new HashMap<>();

    public ScriptMessageMemoryStorage() {
        // имя/ надпись на экране/ кнопка/ прошлый ход/ будущие варианты
        putNewMessage(PLUG_NAME, PLUG_GAME_BUTTON, PLUG_GAME_TEXT, START_BUTTON, Collections.singletonList(START_NAME));
        putNewMessage(COUNT_THEME_NAME, ALL_BUTTONS, COUNT_THEME_GAME_TEXT, CREATE_GAME_BUTTON, Collections.singletonList(PLUG_NAME));
        putNewMessage(CREATE_GAME_NAME, CREATE_GAME_BUTTON, CREATE_GAME_TEXT, START_BUTTON, Collections.singletonList(COUNT_THEME_NAME));
        putNewMessage(PLAY_GAME_NAME, PLAY_GAME_BUTTON, PLAY_GAME_TEXT, START_BUTTON, Collections.singletonList(PLUG_NAME));
        putNewMessage(START_NAME, START_BUTTON, START_TEXT, START_BUTTON, Arrays.asList(PLAY_GAME_NAME, CREATE_GAME_NAME));
    }

    @Override
    public ScriptMessage getStartMessage() {
        return scriptMessages.get(START_NAME);
    }

    @Override
    public ScriptMessage updateScript(ScriptMessage scriptMessage, String text) {
        if (text.equals(BACK_BUTTON)) {
            return scriptMessages.get(scriptMessage.getStepBack());
        }

        List<ScriptMessage> sm = scriptMessages
                .values()
                .stream()
                .filter(x -> x.getButtonText().equals(text)).collect(Collectors.toList());

        if (!sm.isEmpty()) {
            return scriptMessages.get(sm.get(0).getName());
        } else {
            if (scriptMessage.getNext().size() == 1) {
                Optional<String> result = scriptMessage
                        .getNext()
                        .stream()
                        .findFirst();
                if (result.isPresent()) {
                    return scriptMessages.get(result.get());
                }
            }
            return scriptMessage;
        }
    }

    private void putNewMessage(String name, String button, String text, String stepBack, List<String> nextSteps) {
        scriptMessages.put(name,
                new ScriptMessage(name,
                        text,
                        button,
                        stepBack,
                        new HashSet<>(nextSteps)));
    }

    @Override
    public Set<String> getNextButtons(ScriptMessage scriptMessage) {
        return scriptMessage
                .getNext()
                .stream()
                .map(x -> scriptMessages.get(x)
                        .getButtonText())
                .collect(Collectors.toSet());
    }
}
