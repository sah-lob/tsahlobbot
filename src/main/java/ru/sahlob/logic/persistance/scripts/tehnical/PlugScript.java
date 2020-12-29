package ru.sahlob.logic.persistance.scripts.tehnical;

import org.springframework.stereotype.Component;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.scripts.ScriptMessage;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.*;

@Component
public class PlugScript implements ScriptMessage {

    @Override
    public ScriptNames getName() {
        return ScriptNames.PLUG;
    }

    @Override
    public String getMessageText(Person person) {
        return PLUG_GAME_TEXT;
    }

    @Override
    public String getButtonText() {
        return PLUG_GAME_BUTTON;
    }

    @Override
    public Set<String> additionalButton(Person person) {
        return Collections.EMPTY_SET;
    }

    @Override
    public boolean isScriptValid(String message, Person person) {
        return true;
    }

    @Override
    public String getErrorValidMessage() {
        return ERROR_VALID_MESSAGE;
    }

    @Override
    public void doBackWork(String msg, Person person) {
    }

    @Override
    public List<ScriptNames> getNext(Person person, String message) {
        return Collections.singletonList(ScriptNames.START);
    }

    @Override
    public void doWork(String message, Person person) {
        System.out.println(message);
    }
}
