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
    public Person getPersonByTelegramId(long telegramId) {
        return personsRepository.getFirstPersonByTelegramId(telegramId);
    }

    public Person getFirstPersonById(long id) {
        return personsRepository.getFirstPersonById(id);
    }

    @Override
    public Person getPersonByTelegramId(Person person) {
        var p = getPersonByTelegramId(person.getTelegramId());
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
