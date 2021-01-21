package ru.sahlob;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.sahlob.configuration.SettingsConfigurationInfo;
import ru.sahlob.logic.persistance.MainController;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.VarMessage;

import java.util.List;
import java.util.Scanner;


@RunWith(SpringRunner.class)
@SpringBootTest
class EntryPointTest {

    @Autowired
    private MainController mainController;
    @Autowired
    private SettingsConfigurationInfo settingsConfigurationInfo;

    @Test
    void createNewGameByPerson() {
        var person = new Person();
        var chatId = -2L;
        person.setTelegramId(80123808L);
        person.setFirstName("Alexander");
        person.setLastName("Lobachev");
        mainController.startLogic(person, "sdf", chatId);
        mainController.startLogic(person, "Создать игру", chatId);
        mainController.startLogic(person, "Название игры", chatId);
        mainController.startLogic(person, "4", chatId);//кол-во тем
        mainController.startLogic(person, "4", chatId); //кол-во вопросов
        mainController.startLogic(person, "4", chatId); // кол-во ответов
        mainController.startLogic(person, "100", chatId); // стартовая цена
        mainController.startLogic(person, "100", chatId); // шаг цены
        for (int i = 0; i < 4; i++) {
            mainController.startLogic(person, "Тема номер: " + (i + 1), chatId);
            for (int j = 0; j < 4; j++) {
                mainController.startLogic(person, "Вопрос номер: " + (i + 1) + "" + (j + 1), chatId);
                mainController.startLogic(person, "Правильный ответ номер " + (i + 1) + "" + (j + 1) + "1", chatId);
                for (int k = 0; k < 3; k++) {
                    mainController.startLogic(person, "Ответ номер " + (i + 1) + "" + (j + 1) + "" + (k + 2), chatId);
                }
            }
        }
    }

    @Test
    void createNewRoomByPerson() {

    }

}