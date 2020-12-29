package ru.sahlob.logic.persistance.scripts.play.playRoom;

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
                answer.set(answer.get()
                           + "\n"
                           + count.getAndSet(count.get() + 1)
                           + ") "
                           + x.getThemeText() + "\n   Оставшиеся цены:");
                x.getRoomQuestions().forEach(z -> answer.set(answer.get() + "\n   " + z.getPrice()));
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
        return Collections.emptySet();
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
    public String getErrorValidMessage() {
        return null;
    }

    @Override
    public void doBackWork(String message, Person person) {

    }

    @Override
    public List<ScriptNames> getNext(Person person, String message) {
        return Collections.singletonList(ScriptNames.PLUG);
    }

    @Override
    public void doWork(String message, Person person) {
        System.out.println("Выбранная тема: " + message);
        person.getRoom().setStarted(true);
        person.getRoom().setSelectedTheme(message);
    }
}
