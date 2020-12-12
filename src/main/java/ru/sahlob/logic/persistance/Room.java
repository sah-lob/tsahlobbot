package ru.sahlob.logic.persistance;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import ru.sahlob.logic.persistance.game.Game;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@RequiredArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) private Long id;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Game game;

    @OneToMany(mappedBy="room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Person> players = new HashSet<>();

    public void addPlayer(Person player) {
        players.add(player);
    }
}
