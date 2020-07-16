package ru.sahlob;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class Example extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        Message msg = update.getMessage(); // Это нам понадобится
        long chatId = update.getMessage().getChatId();
        String txt = msg.getText();
        try {
            execute(new SendMessage(chatId, "Сам " + txt));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "sah_lob_bot";
    }

    @Override
    public String getBotToken() {
        return "1368157499:AAEHV_03OLS7IVc-3LyxrTalGNpZxyXzS-c";
    }
}
