package ru.sahlob.storage.memory;

import ru.sahlob.logic.persistance.game.Game;

import java.util.HashSet;
import java.util.Set;

public class GameMemoryStorage {

    private Set<Game> games = new HashSet<>();

    public void createNewGame(Game game) {
        games.add(game);
    }
}
