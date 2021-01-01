package ru.sahlob.logic.persistance.scripts.play.createRoom;

import org.springframework.stereotype.Component;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.scripts.ScriptMessage;
import ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.PLAYER_ID_BUTTON;
import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames.PLAYER_ID;
import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames.START;

@Component
public class PlayerIdScript implements ScriptMessage {

    @Override
    public ScriptNames getName() {
        return PLAYER_ID;
    }

    @Override
    public String getMessageText(Person person) {
        return "Ваш id: " + person.getId();
    }

    @Override
    public String getButtonText() {
        return PLAYER_ID_BUTTON;
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
