package ru.sahlob.logic.persistance;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import ru.sahlob.logic.persistance.game.Game;

import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Game game;
}
