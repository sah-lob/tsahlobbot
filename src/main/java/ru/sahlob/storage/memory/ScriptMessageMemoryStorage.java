package ru.sahlob.storage.memory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.game.Game;
import ru.sahlob.logic.persistance.scripts.ScriptMessage;
import ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames;
import ru.sahlob.storage.interfaces.ScriptMessageStorage;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.BACK_BUTTON;
import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames.START;

@Service
public class ScriptMessageMemoryStorage implements ScriptMessageStorage {

    private final Map<ScriptNames, ScriptMessage> scriptMessages;

    @Autowired
    public ScriptMessageMemoryStorage(Set<ScriptMessage> set) {
        scriptMessages = set.stream()
                .collect(Collectors.toMap(ScriptMessage::getName,
                        Function.identity(), (first, second) -> first));
    }

    @Override
    public ScriptMessage getStartMessage() {
        return scriptMessages.get(START);
    }

    @Override
    public Set<String> getNextButtons(ScriptMessage scriptMessage) {
        return scriptMessage
                .getNext(null)
                .stream()
                .map(x -> scriptMessages.get(x)
                        .getButtonText())
                .collect(Collectors.toSet());
    }

    @Override
    public ScriptMessage updateScript(Person person, String text) {
        ScriptMessage scriptMessage = person.getScriptMessage();
        if (scriptMessage.isScriptValid(text)) {
            scriptMessage.doWork(text, person);
        return text.equals(BACK_BUTTON)
                ? scriptMessages.get(scriptMessage.getStepBack())
                : getNextScriptMessage(person, text);
        }
        return scriptMessage;
//        scriptMessage.doWork(text, person);
    }

    private ScriptMessage getNextScriptMessage(Person person, String text) {
        ScriptMessage nextScriptMessage;
        if (person.isScriptCycle() && person.getScriptCycleCount() > person.getScriptCycleNum()) {
            person.incrementScriptCycleNum();
            nextScriptMessage = person.getScriptMessage();
        } else {
            nextScriptMessage = getNextScriptMessageFromScriptMessages(person, text);
        }
        return nextScriptMessage;
    }

    private ScriptMessage getNextScriptMessageFromScriptMessages(Person person, String text) {
        ScriptMessage scriptMessage = person.getScriptMessage();
        List<ScriptMessage> sm = getScriptMessagesWithThisText(text);
        ScriptMessage nextScriptMessage = scriptMessage;
        if (sm.isEmpty()) {
            if (scriptMessage.getNext(person.getLastGame()).size() == 1) {
                nextScriptMessage = scriptMessages.get(scriptMessage.getNext(person.getLastGame()).get(0));
            } else if (scriptMessage.getNext(person.getLastGame()).size() > 1) {
                List<ScriptNames> result = scriptMessage.getNext(person.getLastGame());
                for (ScriptNames scriptNames : result) {
                    Game game = person.getLastGame();
                    if (game.getScriptNameCount(scriptNames) > game.getScriptNameIntroducece(scriptNames)) {
                        nextScriptMessage = scriptMessages.get(scriptNames);
                        break;
                    }
                }
            }
        } else {
            nextScriptMessage = scriptMessages.get(sm.get(0).getName());
        }
        return nextScriptMessage;
    }

    private List<ScriptMessage> getScriptMessagesWithThisText(String text) {
        return scriptMessages
                .values()
                .stream()
                .filter(x ->
                        x.getButtonText()
                                .equals(text))
                .collect(Collectors.toList());
    }
}
