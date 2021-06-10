package com.example.TravelBot.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BotConfig extends TelegramLongPollingBot {

    @Value("${telegrambot.webHookPath}")
    String webHookPath;

    @Value("${telegrambot.botName}")
    String botName;

    @Value("${telegrambot.botToken}")
    String botToken;


    public BotConfig(DefaultBotOptions options) {
        super(options);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.getMessage() != null && update.getMessage().hasText()){
            long chatId = update.getMessage().getChatId();
            try{
                execute(new SendMessage(chatId, update.getMessage().getText()));
            } catch (TelegramApiException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return botToken;
    }

    @Override
    public String getBotToken() {
        return botName;
    }
}
