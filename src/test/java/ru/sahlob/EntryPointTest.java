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

import java.util.Scanner;


@RunWith(SpringRunner.class)
@SpringBootTest
class EntryPointTest {

    @Autowired
    private MainController mainController;
    @Autowired
    private SettingsConfigurationInfo settingsConfigurationInfo;

    @Test
    void onUpdateReceivedTest() {
        var person = new Person();
        person.setTelegramId(-1L);
        person.setFirstName("TestName");
        person.setLastName("TestLastName");
        person.setUserName("testUserName");
        var chatId = -2L;
        VarMessage varMessage = null;
        while (true) {
            String txt = "asdf";
            varMessage = mainController.startLogic(person, txt, chatId);
            System.out.println("-----------------------------------------------------");
            System.out.println(varMessage.getText());
            System.out.println(varMessage.getButtonsText());
            System.out.println("-----------------------------------------------------");
        }
    }

}