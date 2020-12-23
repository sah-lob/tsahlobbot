package ru.sahlob.storage.db;

import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.storage.interfaces.PersonsStorage;

@Service
@Transactional
@Data
public class DBPersonsStorage implements PersonsStorage {

    private final PersonsRepository personsRepository;

    @Override
    public void addPerson(Person person) {
        personsRepository.save(person);
    }

    @Override
    public Person getPerson(long telegramId) {
        return personsRepository.getFirstPersonByTelegramId(telegramId);
    }

    @Override
    public Person getPerson(Person person) {
        var p = getPerson(person.getTelegramId());
        if (p != null) {
            p.incrementMessageCount();
            return p;
        }
        addPerson(person);
        return person;
    }

    @Override
    public void updatePerson(Person person) {
        addPerson(person);
    }


}
