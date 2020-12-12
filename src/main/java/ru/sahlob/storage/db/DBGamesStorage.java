package ru.sahlob.storage.db;

import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sahlob.logic.persistance.game.Game;

import java.util.List;

@Service
@Transactional
@Data
public class DBGamesStorage {

    private final GamesRepository gamesRepository;

    public List<Game> getAllGames() {
        return (List<Game>) gamesRepository.findAll();
    }

    public boolean existsGameById(Long id) {
        return gamesRepository.existsById(id);
    }

    public Game getGameByID(int id) {
        return gamesRepository.findFirstById(id);
    }
}
