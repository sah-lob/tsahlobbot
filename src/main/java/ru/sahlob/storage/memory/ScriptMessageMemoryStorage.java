package ru.sahlob.storage.memory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sahlob.logic.persistance.scripts.ScriptMessage;
import ru.sahlob.storage.interfaces.ScriptMessageStorage;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.BACK_BUTTON;
import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.START_NAME;

@Service
public class ScriptMessageMemoryStorage implements ScriptMessageStorage {

    private final Map<String, ScriptMessage> scriptMessages;

    @Autowired
    public ScriptMessageMemoryStorage(Set<ScriptMessage> set) {
        scriptMessages = set.stream()
                .collect(Collectors.toMap(ScriptMessage::getName,
                        Function.identity(), (first, second) -> first));
//        scriptMessages.entrySet().forEach(System.out::println);
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
