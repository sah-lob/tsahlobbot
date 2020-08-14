package ru.sahlob;

import lombok.Data;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;

@SpringBootApplication
@Data
public class Application implements CommandLineRunner {

    private final EntryPoint example;

    public static void main(final String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(example);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
