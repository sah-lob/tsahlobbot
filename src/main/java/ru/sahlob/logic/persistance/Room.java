package ru.sahlob.logic.persistance;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import ru.sahlob.logic.persistance.game.Game;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Game game;

    @OneToMany(mappedBy="room", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Set<Person> players = new HashSet<>();

    public void addPlayer(Person player) {

        players.add(player);
    }
}
