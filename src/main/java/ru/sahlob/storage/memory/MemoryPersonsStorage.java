package ru.sahlob.storage.memory;

import org.springframework.stereotype.Component;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.storage.interfaces.PersonsStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MemoryPersonsStorage implements PersonsStorage {

    private final List<Person> personList = new ArrayList<>();

    @Override
    public void addPerson(Person person) {
        personList.add(person);
    }

    @Override
    public Person getPerson(long telegramId) {
        List<Person> persons = personList.stream()
                .filter(x -> x.getTelegramId() == telegramId)
                .collect(Collectors.toList());
        return persons.isEmpty() ? null : persons.get(0);
    }

    @Override
    public Person getPerson(Person person) {
            if (getPerson(person.getTelegramId()) == null) {
                addPerson(person);
            } else {
                person = getPerson(person.getTelegramId());
            }
            return person;
    }

    @Override
    public void updatePerson(Person person) {

    }
}
