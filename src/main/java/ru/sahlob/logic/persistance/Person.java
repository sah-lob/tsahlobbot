package ru.sahlob.logic.persistance;

import lombok.Data;
import ru.sahlob.logic.persistance.scripts.ScriptMessage;

@Data
public class Person {
    private Long id;
    private Long telegramId;
    private String firstName;
    private String lastName;
    private String userName;
    private int firstMessageTime;
    private int massageCount;
    private ScriptMessage scriptMessage;
    private ScriptMessage varMessage;
}
