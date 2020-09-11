package ru.sahlob.storage.memory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sahlob.logic.persistance.Person;
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
    }

    @Override
    public ScriptMessage getStartMessage() {
        return scriptMessages.get(START_NAME);
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

    @Override
    public ScriptMessage updateScript(Person person, String text) {
        ScriptMessage scriptMessage = person.getScriptMessage();
        scriptMessage.doWork(text, person);
        return text.equals(BACK_BUTTON)
                ? scriptMessages.get(scriptMessage.getStepBack())
                : getNextScriptMessage(person, text);
    }

    private ScriptMessage getNextScriptMessage(Person person, String text) {
        ScriptMessage nextScriptMessage;
        if (person.isScriptCycle() && person.getScriptCycleCount() > person.getScriptCycleNum()) {
            person.incrementScriptCycleNum();
            nextScriptMessage = person.getScriptMessage();
        } else {
            nextScriptMessage = getNextScriptMessageFromScriptMessages(person, text);
            person.setScriptCycle(nextScriptMessage.isCycleExist());
        }
        return nextScriptMessage;
    }

    private ScriptMessage getNextScriptMessageFromScriptMessages(Person person, String text) {
        ScriptMessage scriptMessage = person.getScriptMessage();
        List<ScriptMessage> sm = getScriptMessagesWithThisText(text);
        ScriptMessage nextScriptMessage;
        if (!sm.isEmpty()) {
            nextScriptMessage = scriptMessages.get(sm.get(0).getName());
        } else {
            if (scriptMessage.getNext().size() == 1) {
                Optional<String> result = scriptMessage
                        .getNext()
                        .stream()
                        .findFirst();
                if (result.isPresent()) {
                    nextScriptMessage = scriptMessages.get(result.get());
                } else {
                    nextScriptMessage = scriptMessage;
                }
            } else {
                nextScriptMessage = scriptMessage;
            }
        }
        return nextScriptMessage;
    }

    private List<ScriptMessage> getScriptMessagesWithThisText(String text) {
        return scriptMessages
                .values()
                .stream()
                .filter(x -> x.getButtonText().equals(text)).collect(Collectors.toList());
    }
}
