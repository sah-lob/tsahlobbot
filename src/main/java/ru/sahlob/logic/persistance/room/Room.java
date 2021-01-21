package ru.sahlob.logic.persistance.room;

import lombok.*;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.VarMessage;
import ru.sahlob.logic.persistance.game.Game;
import ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames;
import ru.sahlob.storage.db.DBPersonsStorage;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Data
@RequiredArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(onlyExplicitlyIncluded = true)
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ToString.Include
    private Long id;

    private Long createdPlayerId;
    private boolean isStarted = false;
    @ElementCollection
    private List<Long> order = new ArrayList<>();
    private Integer orderCount = 0;
    private Integer numberOfRespondents = 0;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private RoomGame roomGameId;
    @OneToMany(mappedBy = "room", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Person> players = new HashSet<>();
    private String selectedTheme;
    private String selectedQuestionPrice;
    @Transient
    private RoomQuestion selectedQuestion;



    public void addPlayer(Person player) {
        players.add(player);
    }

    public Set<Person> getPersonWithoutAdmin() {
        return players.stream().filter(x -> !x.getId().equals(createdPlayerId)).collect(Collectors.toSet());
    }


    public List<VarMessage> varMessagesToOtherPlayers(Long playerId,
                                                      ScriptNames scriptName,
                                                      DBPersonsStorage dbPersonsStorage,
                                                      String text,
                                                      Set<String> buttons) {
        var varMessages = new ArrayList<VarMessage>();
        players
                .stream()
                .filter(x -> !x.getId().equals(playerId))
                .collect(Collectors.toSet())
                .forEach(x -> {
                    x.setScriptMessageName(scriptName);
                    dbPersonsStorage.updatePerson(x);
                    varMessages.add(
                            new VarMessage(
                                    text,
                                    buttons,
                                    x.getTelegramId()));
                });
        return varMessages;
    }

    public void addRoomGame(Game game) {
        RoomGame roomGame = new RoomGame();
        roomGame.setGameName(game.getGameName());
        roomGame.addAllThemes(game.getThemes());
        roomGameId = roomGame;
    }

    public void setOrder() {
        var list = players.stream().map(Person::getId).collect(Collectors.toList());
        Collections.shuffle(list);
        order.addAll(list);
    }

    public long getNextChoosePersonId() {
        return order.get(orderCount);
    }

    public void incrementNextChoosePersonId() {
        if (orderCount + 1 >= order.size()) {
            orderCount = 0;
        } else {
            orderCount++;
        }
    }

    public RoomTheme getSelectedTheme() {
        return roomGameId.getRoomThemes()
                .stream()
                .filter(x ->
                        x.getThemeText().equals(selectedTheme))
                .findFirst()
                .orElse(null);
    }

    public RoomQuestion getSelectedRoomQuestion() {
        var roomTheme = getSelectedTheme();
        int price = Integer.parseInt(selectedQuestionPrice);
        return roomTheme.getRoomQuestions().stream().filter(x -> x.getPrice() == price).findFirst().get();
    }

    public synchronized void incrementNumberOfRespondents() {
        numberOfRespondents++;
    }
}
