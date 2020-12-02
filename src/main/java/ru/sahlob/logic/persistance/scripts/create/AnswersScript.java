package ru.sahlob.logic.persistance.scripts.create;

import org.springframework.stereotype.Component;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.game.Answer;
import ru.sahlob.logic.persistance.game.Game;
import ru.sahlob.logic.persistance.scripts.ScriptMessage;
import ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.*;
import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames.*;

@Component
public class AnswersScript implements ScriptMessage {

    @Override
    public ScriptNames getName() {
        return GAME_ANSWERS;
    }

    @Override
    public String getMessageText(Person person) {
        List<Answer> answerList = person.getLastGame()
                .getLastTheme()
                .getLastQuestion()
                .getAnswers();
        return answerList.isEmpty() ? RIGHT_ANSWER_TEXT : ANSWER_TEXT + " " + (answerList.size() + 1);
    }

    @Override
    public String getButtonText() {
        return ALL_BUTTONS;
    }

    @Override
    public Set<String> additionalButton() {
        return Collections.EMPTY_SET;
    }

    @Override
    public boolean isScriptValid(String message) {
        return true;
    }

    @Override
    public String getErrorValidMessage() {
        return ERROR_VALID_MESSAGE;
    }

    @Override
    public ScriptNames getStepBack() {
        return START;
    }

    @Override
    public List<ScriptNames> getNext(Game game, Person person) {
        if (game == null) {
            return Collections.singletonList(COUNT_ANSWERS);
        }
        var themes = game.getThemes();
        var questions = themes.get(themes.size() - 1).getQuestions();
        var  answers = questions.get(questions.size() - 1).getAnswers();

        var generalCountThemes = game.getScriptNameCount(COUNT_THEMES);
        var generalCountQuestions = game.getScriptNameCount(COUNT_QUESTIONS);
        var generalCountAnswers = game.getScriptNameCount(COUNT_ANSWERS);

        if (answers.size() < generalCountAnswers) {
            return Collections.singletonList(GAME_ANSWERS);
        } else if (questions.size() < generalCountQuestions) {
            return Collections.singletonList(GAME_QUESTIONS);
        } else if (themes.size() < generalCountThemes) {
            return Collections.singletonList(GAME_THEMES);
        } else {
            return Collections.singletonList(PLUG);
        }
    }

    @Override
    public void doWork(String message, Person person) {
        person.getLastGame().getLastTheme().getLastQuestion().addAnswer(message);
        System.out.println(message);
    }
}
