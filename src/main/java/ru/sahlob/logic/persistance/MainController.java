package ru.sahlob.logic.persistance;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import ru.sahlob.logic.persistance.script.ScriptMessageStorage;
import ru.sahlob.storage.PersonsStorage;

import java.util.stream.Collectors;

import static ru.sahlob.logic.persistance.script.ScriptMessageText.ALL_BUTTONS;

@Controller
@Data
@AllArgsConstructor
public class MainController {

    private final ScriptMessageStorage scriptMessageStorage;
    private final PersonsStorage personsStorage;

    public VarMessage startLogic(Person person, String txt, long chatId) {
        person = personsStorage.getPerson(person);
        if (person.getScriptMessage() == null) {
            person.setScriptMessage(scriptMessageStorage.getStartMessage());
        } else {
            person.setScriptMessage(scriptMessageStorage.updateScript(person.getScriptMessage(), txt));
        }
        return new VarMessage(person.getScriptMessage().getMessageText(),
                scriptMessageStorage
                        .getNextButtons(person
                                .getScriptMessage())
                        .stream()
                        .filter(x -> !x.equals(ALL_BUTTONS))
                        .collect(Collectors.toSet()),
                chatId);
    }
}
