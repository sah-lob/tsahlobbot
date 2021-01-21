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
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.ALL_BUTTONS;
import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames.PLUG;
import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames.SELECT_QUESTION;

@Component
@Data
public class SelectQuestionScript implements ScriptMessage {

    private final DBPersonsStorage dbPersonsStorage;

    @Override
    public ScriptNames getName() {
        return SELECT_QUESTION;
    }

    @Override
    public String getMessageText(Person person) {
        var n = new AtomicReference<>("Выберете цену вороса:\n Доступные цены: \n");
        person
                .getRoom()
                .getSelectedTheme()
                .getRoomQuestions()
                .forEach(x -> n.set(n.get() + "\n" + x.getPrice()));
        return String.valueOf(n);
    }

    @Override
    public String getButtonText() {
        return ALL_BUTTONS;
    }

    @Override
    public Set<String> additionalButton(Person person) {
        var room = person.getRoom();
        return room
                .getSelectedTheme()
                .getRoomQuestions()
                .stream()
                .map(x -> String.valueOf(x.getPrice()))
                .collect(Collectors.toSet());
    }

    @Override
    public void doBackWork(String message, Person person) {
    }

    @Override
    public boolean isScriptValid(String message, Person person) {
        return additionalButton(person).contains(message);
    }

    @Override
    public List<ScriptNames> getNext(Person person, String message) {
        return Collections.singletonList(ScriptNames.SELECT_ANSWER);
    }

    @Override
    public void doWork(String message, Person person) {
        var room = person.getRoom();
        room.setSelectedQuestionPrice(message);
        var roomTheme = room.getSelectedTheme();
        room.setSelectedQuestion(roomTheme
                .getRoomQuestions()
                .stream()
                .filter(x -> x.getPrice() == Integer.parseInt(message))
                .findFirst()
                .get());
        dbPersonsStorage.updatePerson(person);
        person.setVarMessagesList(room.varMessagesToOtherPlayers(
                person.getId(),
                PLUG, dbPersonsStorage, "text", Collections.EMPTY_SET));
        System.out.println(message);
    }
}
