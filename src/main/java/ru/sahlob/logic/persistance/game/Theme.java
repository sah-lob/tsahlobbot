package ru.sahlob.logic.persistance.game;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Theme {
    private long id;
    private String themeText;
    private List<Question> questions = new ArrayList<>();
}
