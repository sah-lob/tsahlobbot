package ru.sahlob.storage.db;

import org.springframework.data.repository.CrudRepository;
import ru.sahlob.logic.persistance.Room;

public interface RoomsRepository extends CrudRepository<Room, Integer> {
}
