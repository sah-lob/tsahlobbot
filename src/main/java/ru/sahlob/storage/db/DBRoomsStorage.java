package ru.sahlob.storage.db;

import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.room.Room;
import ru.sahlob.logic.persistance.game.Game;

@Service
@Transactional
@Data
public class DBRoomsStorage {

    private final RoomsRepository roomsRepository;

    public Room createRoom(Person person, Game game) {
        var room = new Room();
        room.setCreatedPlayerId(person.getId());
        room.addRoomGame(game);
        person.setRoom(room);
        return room;
    }

    public void deleteRoomByID(Long id) {
        roomsRepository.delete(getRoomsRepository().findFirstById(id));
    }

    public Room getRoomByID(long id) {
       return roomsRepository.findFirstById(id);
    }

    public boolean isRoomExist(long id) {
        return roomsRepository.existsById(id);
    }
}
