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
    private int answerCount;
    private int themeIntroduced;
    private int questionIntroduced;
    private int answerIntroduced;
    private List<Theme> themes = new ArrayList<>();
}
