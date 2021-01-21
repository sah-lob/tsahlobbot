package ru.sahlob.logic.persistance.scripts.play.playroom;

import lombok.Data;
import org.springframework.stereotype.Component;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.scripts.ScriptMessage;
import ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames;
import ru.sahlob.storage.db.DBPersonsStorage;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.START_GAME_BUTTON;

@Component
@Data
public class StartGameScript implements ScriptMessage {

    private final DBPersonsStorage dbPersonsStorage;

    @Override
    public ScriptNames getName() {
        return ScriptNames.START_GAME;
    }

    @Override
    public String getMessageText(Person person) {
        var room = person.getRoom();
        if (person.getId().equals(room.getNextChoosePersonId())) {
            var answer = new AtomicReference<>("Вы выбираете тему");
            var count = new AtomicReference<>(1);
            var roomGame = room.getRoomGameId();
            var roomThemes = roomGame.getRoomThemes();
            roomThemes.forEach(x-> {
                var roomQuestions = x.getRoomQuestions();
                if (roomQuestions.size() == 0) {
                    answer.set(
                            answer.get()
                            + "\n"
                            + count.getAndSet(count.get() + 1)
                            + ") "  + x.getThemeText() + ". Вопросы в данной теме закончились=)");
                } else {
                    answer.set(answer.get()
                               + "\n"
                               + count.getAndSet(count.get() + 1)
                               + ") "
                               + x.getThemeText() + "\n   Оставшиеся цены:");
                    roomQuestions.forEach(z -> answer.set(answer.get() + "\n   " + z.getPrice()));
                }
            });
            answer.set(answer.get() + "\nВыберете одну из тем");
            return answer.get();
        } else {
            var choosePerson = dbPersonsStorage.getFirstPersonById(room.getNextChoosePersonId());
            return "Игру выбирает " + choosePerson.getFirstName() + " " + choosePerson.getLastName();
        }
    }

    @Override
    public String getButtonText() {
        return START_GAME_BUTTON;
    }

    @Override
    public Set<String> additionalButton(Person person) {
        var room = person.getRoom();
        return room.getRoomGameId().getRoomThemes()
                .stream()
                .map(x -> String.valueOf(x.getThemeText()))
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isScriptValid(String message, Person person) {
        var flag = new AtomicBoolean(false);
        person.getRoom().getRoomGameId().getRoomThemes().forEach(x -> {
            if (x.getThemeText().equals(message)) {
                flag.set(true);
            }
        });
        return flag.get();
    }

    @Override
    public void doBackWork(String message, Person person) {

    }

    @Override
    public List<ScriptNames> getNext(Person person, String message) {
        return Collections.singletonList(ScriptNames.SELECT_QUESTION);
    }

    @Override
    public void doWork(String message, Person person) {
        System.out.println("Выбранная тема: " + message);
        person.getRoom().setStarted(true);
        person.getRoom().setSelectedTheme(message);
    }
}
