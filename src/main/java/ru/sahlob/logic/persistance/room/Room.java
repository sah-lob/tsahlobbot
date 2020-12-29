package ru.sahlob.logic.persistance.room;

import lombok.*;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.game.Game;

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
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private RoomGame RoomGameId;
    @OneToMany(mappedBy = "room", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Person> players = new HashSet<>();
    private String selectedTheme;
    private String selectedQuestionPrice;



    public void addPlayer(Person player) {
        players.add(player);
    }

    public Set<Person> getPersonWithoutAdmin() {
        return players.stream().filter(x -> !x.getId().equals(createdPlayerId)).collect(Collectors.toSet());
    }

    public void addRoomGame(Game game) {
        RoomGame roomGame = new RoomGame();
        roomGame.setGameName(game.getGameName());
        roomGame.addAllThemes(game.getThemes());
        RoomGameId = roomGame;
    }

    public void setOrder() {
        var list = players.stream().map(Person::getId).collect(Collectors.toList());
        Collections.shuffle(list);
        order.addAll(list);
    }

    public long getNextChoosePersonId() {
        return order.get(orderCount);
    }

    public RoomTheme getSelectedTheme() {
        return RoomGameId.getRoomThemes()
                .stream()
                .filter(x ->
                        x.getThemeText().equals(selectedTheme))
                .findFirst()
                .orElse(null);
    }
}
