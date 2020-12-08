package ru.sahlob.logic.persistance.scripts.play;

import org.springframework.stereotype.Component;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.scripts.ScriptMessage;
import ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.PLAY_WITH_FRIENDS_BUTTON;
import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames.*;

@Component
public class PlayWithFriendsScript implements ScriptMessage {

    @Override
    public ScriptNames getName() {
        return PLAY_WITH_FRIENDS;
    }

    @Override
    public String getMessageText(Person person) {
        return "Создать комнату / Присоединиться к комнате";
    }

    @Override
    public String getButtonText() {
        return PLAY_WITH_FRIENDS_BUTTON;
    }

    @Override
    public Set<String> additionalButton() {
        return Collections.EMPTY_SET;
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
        return CREATED_GAMES;
    }

    @Override
    public List<ScriptNames> getNext(Person person) {
        return Collections.singletonList(PLUG);
    }

    @Override
    public void doWork(String message, Person person) {
        System.out.println(message);
    }
}
