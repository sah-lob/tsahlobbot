package ru.sahlob.logic.persistance.game;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Data
@RequiredArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) private Long id;
    private String gameName;
    @ElementCollection(fetch = FetchType.EAGER) private Map<ScriptNames, Integer> counters = new HashMap<>();
    @ElementCollection(fetch = FetchType.EAGER) private Map<ScriptNames, Integer> introduceces = new HashMap<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Theme> themes = new ArrayList<>();
    private Integer startQuestionPrice = -1;
    private Integer stepQuestionPrice = -1;

    public void incrementIntroduce(ScriptNames scriptName) {
        introduceces.put(scriptName,
                introduceces.get(scriptName) == null
                        ? 0
                        : introduceces.get(scriptName) + 1);
    }

    public int getScriptNameCount(ScriptNames scriptName) {
        scriptName = ScriptNames.valueOf(scriptName.name().replaceFirst("GAME", "COUNT"));
        return counters.get(scriptName);
    }

    public int getScriptNameIntroducece(ScriptNames scriptName) {
        return introduceces.get(scriptName) == null ? 0 : introduceces.get(scriptName);
    }

    public void setCounters(ScriptNames names, int num) {
        counters.put(names, num);
    }

    public void addTheme(String theme) {
        themes.add(new Theme(theme));
    }

    public void addQuestionToLastTheme(String question) {
        getLastTheme().addQuestion(question);
    }

    public void addAnswerToLastQuestion(String answer) {
        getLastTheme().getLastQuestion().addAnswer(answer);
    }

    public Theme getLastTheme() {
        return themes.get(themes.size() - 1);
    }
}
