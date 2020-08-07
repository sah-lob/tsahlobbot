package ru.sahlob.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.yaml")
@Getter
public class SettingsConfigurationInfo {
    @Value("${botUserName}")
    private String botUsername;

    @Value("${botToken}")
    private String botToken;
}
