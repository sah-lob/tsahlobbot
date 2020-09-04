package ru.sahlob.logic.persistance;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class VarMessage {
    private String text;
    private Set<String> buttonsText;
    private long chatId;
}