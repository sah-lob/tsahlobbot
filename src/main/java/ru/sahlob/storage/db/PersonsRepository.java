package ru.sahlob.storage.db;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.sahlob.logic.persistance.Person;

@Repository
@Component
public interface PersonsRepository extends CrudRepository<Person, Integer> {
    Person getFirstPersonByTelegramId(Long telegramID);
    Person getFirstPersonById(Long id);
}
