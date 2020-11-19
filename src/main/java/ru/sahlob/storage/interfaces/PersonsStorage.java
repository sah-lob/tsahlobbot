package ru.sahlob.storage.interfaces;

import ru.sahlob.logic.persistance.Person;

public interface PersonsStorage {
    void addPerson(Person person);
    Person getPerson(long telegramId);
    Person getPerson(Person person);
    void updatePerson(Person person);
}
