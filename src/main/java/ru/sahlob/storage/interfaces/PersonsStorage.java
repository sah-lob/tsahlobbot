package ru.sahlob.storage.interfaces;

import ru.sahlob.logic.persistance.Person;

public interface PersonsStorage {
    void addPerson(Person person);
    Person getPersonByTelegramId(long telegramId);
    Person getPersonByTelegramId(Person person);
    void updatePerson(Person person);
}
