package ru.sahlob.storage.db;

import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.Room;
import ru.sahlob.logic.persistance.game.Game;

@Service
@Transactional
@Data
public class DBRoomsStorage {

    private final RoomsRepository roomsRepository;

    public Room createRoom(Person person, Game game) {
        Room room = new Room();
        room.addPlayer(person);
        room.setGame(game);
        person.setRoom(room);
//        room = roomsRepository.save(room);
        return room;
    }
}
