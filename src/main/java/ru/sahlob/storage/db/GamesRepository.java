package ru.sahlob.storage.db;

import org.springframework.data.repository.CrudRepository;
import ru.sahlob.logic.persistance.game.Game;

public interface GamesRepository extends CrudRepository<Game, Integer> {
    boolean existsById(long id);
    Game findFirstById(long id);
}
