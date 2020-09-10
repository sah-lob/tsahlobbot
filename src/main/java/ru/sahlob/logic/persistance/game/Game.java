package ru.sahlob.logic.persistance.game;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Game {
    private long id;
    private String gameName;
    private int themeCount;
    private int questionCount;
    private List<Theme> themes = new ArrayList<>();
}
