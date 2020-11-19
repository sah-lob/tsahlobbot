package ru.sahlob.storage;

import lombok.Data;
import org.springframework.stereotype.Component;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.storage.db.DBPersonsStorage;
import ru.sahlob.storage.interfaces.PersonsStorage;

@Component
@Data
public class MainPersonsStorage implements PersonsStorage {

    private final DBPersonsStorage personsStorage;

    @Override
    public void addPerson(Person person) {
        personsStorage.addPerson(person);
    }

    @Override
    public Person getPerson(long telegramId) {
        return personsStorage.getPerson(telegramId);
    }

    @Override
    public Person getPerson(Person person) {
        return personsStorage.getPerson(person);
    }

    public void updatePerson(Person person) {
        personsStorage.updatePerson(person);
    }
}
