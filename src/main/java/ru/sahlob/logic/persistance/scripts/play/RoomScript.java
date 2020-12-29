package ru.sahlob.logic.persistance.scripts.play;

import lombok.Data;
import org.springframework.stereotype.Component;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.VarMessage;
import ru.sahlob.logic.persistance.scripts.ScriptMessage;
import ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames;
import ru.sahlob.storage.db.DBPersonsStorage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.ALL_NUM;
import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.START_GAME_BUTTON;
import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames.JOINT_ROOM;
import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames.ROOM;

@Component
@Data
public class RoomScript implements ScriptMessage {

    private final DBPersonsStorage dbPersonsStorage;

    @Override
    public ScriptNames getName() {
        return ROOM;
    }

    @Override
    public String getMessageText(Person person) {
        if (person.getLastPreviousScriptMessageName().equals(JOINT_ROOM)) {
            return "Вы присоединились к комнате. Ждите пока подключатся остальные игроки.";
        }
        return "Комната создана. id комнаты: " + person.getRoom().getId() + ". Сообщите id комнаты другим игрокам и жидайте пока подключатся другие игроки.";
    }

    @Override
    public String getButtonText() {
        return ALL_NUM;
    }

    @Override
    public Set<String> additionalButton() {
        return Collections.emptySet();
    }

    @Override
    public boolean isScriptValid(String message, Person person) {
        return person.getRoom().getCreatedPlayerId().equals(person.getId())
               && message.equals(START_GAME_BUTTON);
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
        return Collections.singletonList(ScriptNames.START_GAME);
    }

    @Override
    public void doWork(String message, Person person) {
        var room = person.getRoom();
        room.setOrder();
        var otherPlayers = room.getPersonWithoutAdmin();
        var varMessages = new ArrayList<VarMessage>();
        otherPlayers.forEach(x -> {
            x.setScriptMessageName(ScriptNames.START_GAME);
            dbPersonsStorage.updatePerson(x);
            varMessages.add(
                    new VarMessage(
                            "Поехали",
                            Collections.EMPTY_SET,
                            x.getTelegramId()));
        });
        person.setVarMessagesList(varMessages);
        System.out.println(message);
    }
}
