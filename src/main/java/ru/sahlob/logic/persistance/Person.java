package ru.sahlob.logic.persistance;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import ru.sahlob.logic.persistance.game.Game;
import ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Transactional
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) private Long id;
    private Long telegramId;
    private String firstName;
    private String lastName;
    private String userName;
    private int firstMessageTime;
    private int massageCount;
    private ScriptNames scriptMessageName;
    private boolean isScriptCycle;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Game> games = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Room room;


    public int getScriptCycleCount() {
        return getLastGame().
                getScriptNameCount(scriptMessageName);
    }

    public int getScriptCycleNum() {
        return getLastGame().getScriptNameIntroducece(scriptMessageName);
    }

    public void incrementScriptCycleNum() {
        getLastGame().incrementIntroduce(scriptMessageName);
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
