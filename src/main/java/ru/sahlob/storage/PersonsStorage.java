package ru.sahlob.storage;

import ru.sahlob.logic.persistance.Person;

public interface PersonsStorage {
    void addPerson(Person person);
    Person getPerson(long telegramId);
    Person getPerson(Person person);
}
