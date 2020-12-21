package ru.sahlob.logic.persistance.scripts.play;

import lombok.Data;
import org.springframework.stereotype.Component;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.VarMessage;
import ru.sahlob.logic.persistance.scripts.ScriptMessage;
import ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames;
import ru.sahlob.storage.db.DBRoomsStorage;
import ru.sahlob.util.Utils;

import java.util.*;

import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.*;
import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames.*;

@Component
@Data
public class JoinRoomScript implements ScriptMessage {

    private final DBRoomsStorage dbRoomsStorage;

    @Override
    public ScriptNames getName() {
        return JOINT_ROOM;
    }

    @Override
    public String getMessageText(Person person) {
        return CREATE_ROOM_TEXT;
    }

    @Override
    public String getButtonText() {
        return JOIN_ROOM_BUTTON;
    }

    @Override
    public Set<String> additionalButton() {
        return Collections.emptySet();
    }

    @Override
    public boolean isScriptValid(String message, Person person) {
        if (Utils.checkTheStringContainsOnlyNumbers(message)) {
            return dbRoomsStorage.isRoomExist(Long.parseLong(message));
        }
        return false;
    }

    @Override
    public String getErrorValidMessage() {
        return null;
    }

    @Override
    public void doBackWork(String message, Person person) {
        person.setRoom(null);
    }

    @Override
    public List<ScriptNames> getNext(Person person, String message) {
        return Collections.singletonList(PLUG);
    }

    @Override
    public void doWork(String message, Person person) {
        var room = dbRoomsStorage.getRoomByID(Long.parseLong(message));
        var text = person.getFirstName() + " " + person.getLastName() + " в комнате";
        var adminText = person.getFirstName() + " " + person.getLastName() + " в комнате";
        Set<String> buttons = Collections.emptySet();
        Set<String> adminButtons = new HashSet<>(Collections.singletonList(START_GAME_BUTTON));
        var varMessages = new ArrayList<VarMessage>();
        room.getPlayers().forEach(x -> {
            if (x.getId().equals(room.getCreatedPlayerId())) {
                varMessages.add(
                        new VarMessage(
                                adminText,
                                adminButtons,
                                x.getTelegramId()));
            } else {
                varMessages.add(
                        new VarMessage(
                                text,
                                buttons,
                                x.getTelegramId()));
            }}
        );
        person.setVarMessagesList(varMessages);
        room.addPlayer(person);
        if (person.getRoom() == null) {
            person.setRoom(room);
        }
    }
}
