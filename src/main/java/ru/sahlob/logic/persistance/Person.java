package ru.sahlob.logic.persistance;

import lombok.Data;
import ru.sahlob.logic.persistance.game.Game;
import ru.sahlob.logic.persistance.scripts.ScriptMessage;
import ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames;

import java.util.ArrayList;
import java.util.List;

@Data
public class Person {
    private Long id;
    private Long telegramId;
    private String firstName;
    private String lastName;
    private String userName;
    private int firstMessageTime;
    private int massageCount;
    private ScriptMessage scriptMessage;
    private boolean isScriptCycle;
    private ScriptMessage varMessage;
    private final List<Game> games = new ArrayList<>();


    public int getScriptCycleCount() {
        return getLastGame().
                getScriptNameCount(scriptMessage.getName());
    }

    public int getScriptCycleNum() {
        return getLastGame().getScriptNameIntroducece(scriptMessage.getName());
    }

    public void incrementScriptCycleNum() {
        getLastGame().incrementIntroduce(scriptMessage.getName());
    }

    public void incrementScriptCycleNum(ScriptNames scriptNames) {
        getLastGame().incrementIntroduce(scriptNames);
    }
    public void addNewGame(Game game) {
        games.add(game);
    }
    public Game getLastGame() {
        return games.get(games.size() - 1);
    }
}
