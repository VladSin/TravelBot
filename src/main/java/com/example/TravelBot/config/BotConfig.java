package com.example.TravelBot.config;

import com.example.TravelBot.TelegramBot;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContext;
import org.telegram.telegrambots.bots.DefaultBotOptions;

@Getter
@Setter
@Component
public class BotConfig {

    @Value("${telegrambot.webPath}")
    private String webPath;

    @Value("${telegrambot.botName}")
    private String botName;

    @Value("${telegrambot.botToken}")
    private String botToken;

    @Bean
    public TelegramBot telegramBot() {
        DefaultBotOptions options = ApiContext.getInstance(DefaultBotOptions.class);

        TelegramBot telegramBot = new TelegramBot(options);
        telegramBot.setBotUserName(botName);
        telegramBot.setBotToken(botToken);
        telegramBot.setWebHookPath(webPath);

        return telegramBot;
    }
}
