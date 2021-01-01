package ru.sahlob.logic.persistance.scripts.play.playroom;

import org.springframework.stereotype.Component;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.scripts.ScriptMessage;
import ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.ALL_BUTTONS;
import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames.SELECT_QUESTION;

@Component
public class SelectQuestion implements ScriptMessage {

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
    public List<ScriptNames> getNext(Person person, String message) {
        return Collections.singletonList(ScriptNames.PLUG);
    }

    @Override
    public void doWork(String message, Person person) {
        System.out.println(message);
    }
}
