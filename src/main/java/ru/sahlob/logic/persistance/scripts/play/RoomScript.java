package ru.sahlob.logic.persistance.scripts.play;

import org.springframework.stereotype.Component;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.scripts.ScriptMessage;
import ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.ALL_NUM;
import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames.PLUG;
import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames.ROOM;

@Component
public class RoomScript implements ScriptMessage {

    @Override
    public ScriptNames getName() {
        return ROOM;
    }

    @Override
    public String getMessageText(Person person) {
        return "Комната создана. Ожидайте пока подключатся другие игроки.";
    }

    @Override
    public String getButtonText() {
        return ALL_NUM;
    }

    @Override
    public Set<String> additionalButton() {
        return Collections.emptySet();
    }

    @Override
    public boolean isScriptValid(String message) {
        return true;
    }

    @Override
    public String getErrorValidMessage() {
        return null;
    }

    @Override
    public ScriptNames getStepBack() {
        return null;
    }

    @Override
    public List<ScriptNames> getNext(Person person, String message) {
        return Collections.singletonList(PLUG);
    }

    @Override
    public void doWork(String message, Person person) {
        System.out.println(message);
    }
}
