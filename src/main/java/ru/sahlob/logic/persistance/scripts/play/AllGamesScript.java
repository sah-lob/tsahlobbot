package ru.sahlob.logic.persistance.scripts.play;

import lombok.Data;
import org.springframework.stereotype.Component;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.scripts.ScriptMessage;
import ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames;
import ru.sahlob.storage.db.DBGamesStorage;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.ALL_GAMES_BUTTON;
import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.BACK_BUTTON;

@Component
@Data
public class AllGamesScript implements ScriptMessage {

    private final DBGamesStorage dbGamesStorage;

    @Override
    public ScriptNames getName() {
        return ScriptNames.ALL_GAMES;
    }

    @Override
    public String getMessageText(Person person) {
        AtomicReference<String> mes = new AtomicReference<>("");
        var list = dbGamesStorage.getAllGames();
        list.forEach(x -> mes.set(
                mes.get()
                + "\n"
                + "Имя: " + x.getGameName()
                + " id: " + x.getId()));
        return mes.get();
    }

    @Override
    public String getButtonText() {
        return ALL_GAMES_BUTTON;
    }

    @Override
    public Set<String> additionalButton() {
        return Collections.emptySet();
    }

    @Override
    public boolean isScriptValid(String message, Person person) {
        return message.equals(BACK_BUTTON);
    }

    @Override
    public String getErrorValidMessage() {
        return null;
    }

    @Override
    public void doBackWork(String msg, Person person) {

    }

    @Override
    public List<ScriptNames> getNext(Person person, String message) {
        return Collections.emptyList();
    }

    @Override
    public void doWork(String message, Person person) {
        System.out.println(message);
    }
}
