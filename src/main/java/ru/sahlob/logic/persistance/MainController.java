package ru.sahlob.logic.persistance;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import ru.sahlob.logic.persistance.script.ScriptMessageStorage;
import ru.sahlob.storage.PersonsStorage;

@Controller
@Data
@AllArgsConstructor
public class MainController {

    private final ScriptMessageStorage scriptMessageStorage;
    private final PersonsStorage personsStorage;


    public VarMessage startLogic(Person person, String txt, long chatId) {
        person = personsStorage.getPerson(person);
        if (person.getScriptMessage() == null) {
            person.setScriptMessage(scriptMessageStorage.get());
        } else {
            person.setScriptMessage(scriptMessageStorage.updateScript(person.getScriptMessage(), txt));
        }
        return new VarMessage(person.getScriptMessage().getMessageText(),
                person.getScriptMessage().getNext(),
                chatId);
    }
}
