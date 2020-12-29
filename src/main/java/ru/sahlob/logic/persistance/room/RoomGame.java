package ru.sahlob.logic.persistance.room;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import ru.sahlob.logic.persistance.game.Theme;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class RoomGame {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ToString.Include
    private Long id;
    private String gameName;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RoomTheme> roomThemes = new ArrayList<>();

    public void addAllThemes(List<Theme> themes) {
        themes.forEach(x -> {
            var roomTheme = new RoomTheme();
            roomTheme.setThemeText(x.getThemeText());
            roomTheme.addAllQuestions(x.getQuestions());
            roomThemes.add(roomTheme);
        });
    }

    public List<RoomTheme> getRoomThemes() {
        roomThemes.removeIf(RoomTheme::isAllThemeAsked);
        return roomThemes;
    }
}
