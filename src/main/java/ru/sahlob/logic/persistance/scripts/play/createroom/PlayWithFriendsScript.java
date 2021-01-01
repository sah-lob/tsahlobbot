package ru.sahlob.logic.persistance.scripts.play.createroom;

import org.springframework.stereotype.Component;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.scripts.ScriptMessage;
import ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames;

import java.util.Arrays;
import java.util.List;

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
    public void doBackWork(String msg, Person person) {
    }

    @Override
    public List<ScriptNames> getNext(Person person, String message) {
        return Arrays.asList(CREATE_ROOM, JOINT_ROOM);
    }

    @Override
    public void doWork(String message, Person person) {
        System.out.println(message);
    }
}
