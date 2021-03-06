package ru.sahlob.logic.persistance;

import lombok.*;
import org.springframework.transaction.annotation.Transactional;
import ru.sahlob.logic.persistance.game.Game;
import ru.sahlob.logic.persistance.room.Room;
import ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames.START;

@Entity
@Data
@RequiredArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Transactional
@ToString(onlyExplicitlyIncluded = true)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ToString.Include
    private Long id;
    private Long telegramId;
    private Integer gamePoints = 0;
    private String firstName;
    private String lastName;
    private String userName;
    private int firstMessageTime;
    private int massageCount = 0;
    private ScriptNames scriptMessageName;
    @ElementCollection
    private List<ScriptNames> previousScriptMessageNameList = new ArrayList<>();
    private boolean isScriptCycle;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Game> games = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Room room;
    @Transient
    private List<VarMessage> varMessagesList = new ArrayList<>();


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

    public void incrementMessageCount() {
        massageCount = massageCount++;
    }

    public void addNewGame(Game game) {
        games.add(game);
    }

    public Game getLastGame() {
        return games.get(games.size() - 1);
    }

    public ScriptNames getLastPreviousScriptName() {
        if (previousScriptMessageNameList.size() > 0) {
            return previousScriptMessageNameList.get(previousScriptMessageNameList.size() - 1);
        } else {
            return START;
        }
    }

    public void addPreviousScriptName(ScriptNames scriptNames) {
        previousScriptMessageNameList.add(scriptNames);
    }

    public void deleteLastPreviousScriptName() {
        if (!previousScriptMessageNameList.isEmpty()) {
            previousScriptMessageNameList.remove(previousScriptMessageNameList.size() - 1);
        }
    }

    public ScriptNames getLastPreviousScriptMessageName() {
        return previousScriptMessageNameList.get(previousScriptMessageNameList.size() - 1);
    }
}
