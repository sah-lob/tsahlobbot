package ru.sahlob.logic.persistance.scripts.play.createRoom;

import lombok.Data;
import org.springframework.stereotype.Component;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.scripts.ScriptMessage;
import ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames;
import ru.sahlob.storage.db.DBGamesStorage;
import ru.sahlob.storage.db.DBPersonsStorage;
import ru.sahlob.storage.db.DBRoomsStorage;
import ru.sahlob.util.Utils;

import java.util.Arrays;
import java.util.List;

import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.ALL_GAMES_BUTTON;
import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.CREATE_ROOM_BUTTON;
import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames.*;

@Component
@Data
public class CreateRoomScript implements ScriptMessage {

    private final DBPersonsStorage dbPersonsStorage;
    private final DBGamesStorage dbGamesStorage;
    private final DBRoomsStorage dbRoomsStorage;

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
    public boolean isScriptValid(String message, Person person) {
        if (Utils.checkTheStringContainsOnlyNumbers(message)) {
            return dbGamesStorage.existsGameById(Long.parseLong(message));
        }
        return message.equals(ALL_GAMES_BUTTON);
    }

    @Override
    public void doBackWork(String msg, Person person) {
        var room = person.getRoom();
        if (room != null) {
            person.setRoom(null);
            dbPersonsStorage.updatePerson(person);
            if (room.getPlayers().size() <= 1) {
                dbRoomsStorage.deleteRoomByID(room.getId());
            }
        }
    }

    @Override
    public List<ScriptNames> getNext(Person person, String message) {
        return Arrays.asList(ALL_GAMES, ROOM);
    }

    @Override
    public void doWork(String message, Person person) {
        if (Utils.checkTheStringContainsOnlyNumbers(message)) {
            dbRoomsStorage.createRoom(person, dbGamesStorage.getGameByID(Integer.parseInt(message)));
        }
        System.out.println(message);
    }
}
