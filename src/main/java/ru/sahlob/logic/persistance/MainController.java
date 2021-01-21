package ru.sahlob.logic.persistance;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import ru.sahlob.storage.MainPersonsStorage;
import ru.sahlob.storage.interfaces.ScriptMessageStorage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.ALL_BUTTONS;
import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.ALL_NUM;

@Controller
@Data
@AllArgsConstructor
public class MainController {

    private final ScriptMessageStorage scriptMessageStorage;
    private final MainPersonsStorage personsStorage;

    public List<VarMessage> startLogic(Person person, String txt, long chatId) {
        person = personsStorage.getPersonByTelegramId(person);
        if (person.getScriptMessageName() == null) {
            person.setPreviousScriptMessageNameList(new ArrayList<>());
            person.setScriptMessageName(scriptMessageStorage.getStartMessage().getName());
        } else {
            person.setScriptMessageName(scriptMessageStorage.updateScript(person, txt).getName());
        }
        personsStorage.updatePerson(person);
        List<VarMessage> varMessageList = new ArrayList<>();
        if (!scriptMessageStorage.getScriptMessage(person).automaticNextScript(person)) {
            var firstVarMessage = new VarMessage(scriptMessageStorage.getScriptMessage(person).getMessageText(person),
                    scriptMessageStorage
                            .getNextButtons(scriptMessageStorage.getScriptMessage(person), person)
                            .stream()
                            .filter(x -> !x.equals(ALL_BUTTONS))
                            .filter(x -> !x.equals(ALL_NUM))
                            .collect(Collectors.toSet()),
                    chatId);
            varMessageList.add(firstVarMessage);
        } else {
            var firstVarMessage = new VarMessage(
                    scriptMessageStorage.getScriptMessage(person).getAutomaticMessageText(person),
                    Collections.emptySet(),
                    chatId);
            varMessageList.add(firstVarMessage);
        }
        varMessageList.addAll(person.getVarMessagesList());
        return varMessageList;
    }
}
