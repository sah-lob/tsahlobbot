package ru.sahlob;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.sahlob.configuration.SettingsConfigurationInfo;
import ru.sahlob.logic.persistance.MainController;
import ru.sahlob.logic.persistance.Person;
import ru.sahlob.logic.persistance.VarMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static ru.sahlob.logic.persistance.scripts.tehnical.ScriptMessageText.BACK_BUTTON;

@EqualsAndHashCode(callSuper = true)
@Component
@Data
public class EntryPoint extends TelegramLongPollingBot {

    private final MainController mainController;
    private final SettingsConfigurationInfo settingsConfigurationInfo;

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        Message msg = update.getMessage() != null ? update.getMessage() : update.getCallbackQuery().getMessage();
        Person person = getPerson(msg.getFrom(), msg.getChat(), msg.getChatId());
        long chatId = msg.getChatId();
        String txt = update.getMessage() != null ? update.getMessage().getText() : update.getCallbackQuery().getData();
        System.out.println("Start message: " + txt);
        var varMessageList = mainController.startLogic(person, txt, chatId);
        varMessageList.forEach(x -> {
            try {
                sendMessage(x);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        });
    }

    private Person getPerson(User user, Chat chat, Long chatID) {
        Person person = new Person();
        person.setTelegramId(chatID);
        person.setFirstName(chat.getFirstName());
        person.setLastName(chat.getLastName());
        person.setUserName(user.getUserName());
        return person;
    }

    public void sendMessage(VarMessage varMessage) throws TelegramApiException {
        execute(new SendMessage()
                .setChatId(varMessage.getChatId())
                .setText(varMessage.getText())
                .setReplyMarkup(createKeyBoard(varMessage.getButtonsText())));
    }

    private InlineKeyboardMarkup createKeyBoard(Set<String> buttonsText) {
        List<String> newList = Lists.newArrayList(buttonsText);
        newList.add(BACK_BUTTON);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(new ArrayList<>());

        int j = 0;
        for (int i = 0; i < newList.size(); i++) {
            if (i != 0 && i % 2 == 0) {
                rowList.add(new ArrayList<>());
                j++;
            }
            rowList.get(j).add(new InlineKeyboardButton()
                    .setText(newList.get(i))
                    .setCallbackData(newList.get(i)));
        }
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    @Override
    public final String getBotUsername() {
        return settingsConfigurationInfo.getBotUsername();
    }

    @Override
    public final String getBotToken() {
        return settingsConfigurationInfo.getBotToken();
    }


}
