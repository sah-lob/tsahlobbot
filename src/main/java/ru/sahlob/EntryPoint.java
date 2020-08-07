package ru.sahlob;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.sahlob.configuration.SettingsConfigurationInfo;

import java.util.Calendar;

@EqualsAndHashCode(callSuper = true)
@Component
@Data
public class EntryPoint extends TelegramLongPollingBot {

    private final SettingsConfigurationInfo settingsConfigurationInfo;

    @Override
    public void onUpdateReceived(Update update) {
        Message msg = update.getMessage(); // Это нам понадобится
        long chatId = msg.getChatId();
        String txt = msg.getText();
        try {
            Calendar calendar = Calendar.getInstance();
            int seconds = calendar.get(Calendar.SECOND);
            if (seconds % 2 == 0) {
                execute(new SendMessage(chatId, "Сам " + txt));
            } else {
                execute(new SendMessage(chatId, "Ты " + txt));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return settingsConfigurationInfo.getBotUsername();
    }

    @Override
    public String getBotToken() {
        return settingsConfigurationInfo.getBotToken();
    }
}
