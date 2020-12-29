package ru.sahlob.storage.db;

import org.springframework.data.repository.CrudRepository;
import ru.sahlob.logic.persistance.room.Room;

public interface RoomsRepository extends CrudRepository<Room, Integer> {

    Room findFirstById(long id);
    boolean existsById(long id);
}
