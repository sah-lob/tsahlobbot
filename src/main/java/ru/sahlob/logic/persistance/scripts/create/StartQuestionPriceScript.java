package ru.sahlob.logic.persistance.scripts.create;

import org.springframework.stereotype.Component;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.scripts.ScriptMessage;
import ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames;
import ru.sahlob.util.Utils;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.ALL_BUTTONS;
import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptNames.START;

@Component
public class StartQuestionPriceScript implements ScriptMessage {

    @Override
    public ScriptNames getName() {
        return ScriptNames.START_QUESTION_PRICE;
    }

    @Override
    public String getMessageText(Person person) {
        return "Введите начальную стоимость вопросов \nМинимум 1 \n Максимум 1000";
    }

    @Override
    public String getButtonText() {
        return ALL_BUTTONS;
    }

    @Override
    public Set<String> additionalButton(Person person) {
        return Collections.emptySet();
    }

    @Override
    public boolean isScriptValid(String message, Person person) {
        return Utils.checkTheStringContainsOnlyNumbersBetweenInRange(message, 1, 1000);
    }

    @Override
    public String getErrorValidMessage() {
        return "Что-то вы ввели не так=(";
    }

    @Override
    public void doBackWork(String msg, Person person) {

    }

    @Override
    public List<ScriptNames> getNext(Person person, String message) {
        return Collections.singletonList(ScriptNames.STEP_QUESTION_PRICE);
    }

    @Override
    public void doWork(String message, Person person) {
        System.out.println("start price: " + message);
        var price = Integer.parseInt(message);
        person.getLastGame().setStartQuestionPrice(price);
    }
}
