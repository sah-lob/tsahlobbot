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
    public Person getPersonByTelegramId(long telegramId) {
        return personsStorage.getPersonByTelegramId(telegramId);
    }

    @Override
    public Person getPersonByTelegramId(Person person) {
        return personsStorage.getPersonByTelegramId(person);
    }

    public void updatePerson(Person person) {
        personsStorage.updatePerson(person);
    }
}
