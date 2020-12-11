package ru.sahlob.logic.persistance.scripts.play;

import org.springframework.stereotype.Component;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.scripts.ScriptMessage;
import ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.CREATE_ROOM_BUTTON;
import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames.*;

@Component
public class CreateRoomScript implements ScriptMessage {

    @Override
    public ScriptNames getName() {
        return CREATE_ROOM;
    }

    @Override
    public String getMessageText(Person person) {
        return "Выберете игру. Введите id, посмотрите топ сыгранных игр, или все созданные игры.[топ игр, все игры]";
    }

    @Override
    public String getButtonText() {
        return CREATE_ROOM_BUTTON;
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
        return START;
    }

    @Override
    public List<ScriptNames> getNext(Person person, String message) {
        return Collections.singletonList(ALL_GAMES);
    }

    @Override
    public void doWork(String message, Person person) {
        System.out.println(message);
    }
}
