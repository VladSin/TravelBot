package com.example.TravelBot.config;

import com.example.TravelBot.entity.CityEntity;
import com.example.TravelBot.service.CityService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Getter
@Setter
@Component
public class BotConfig extends TelegramLongPollingBot {

    @Value("${telegrambot.botName}")
    private String botName;

    @Value("${telegrambot.botToken}")
    private String botToken;

    private final TelegramBotsApi telegramBotsApi;
    private final CityService cityService;

    @Autowired
    public BotConfig(TelegramBotsApi telegramBotsApi, CityService cityService) {
        this.telegramBotsApi = telegramBotsApi;
        this.cityService = cityService;
    }

    @Override
    public String getBotUsername() {
        return botToken;
    }

    @Override
    public String getBotToken() {
        return botName;
    }

    @Override
    public void onUpdateReceived(Update update) {

        String inMessage;
        String chatId;

        if (update.hasEditedMessage()) {
            inMessage = update.getEditedMessage().getText();
            chatId = update.getEditedMessage().getChatId().toString();
        } else {
            inMessage = update.getMessage().getText();
            chatId = update.getMessage().getChatId().toString();
        }

        switch (inMessage) {
            case "/start":
                sendMessages(chatId, "Welcome to TravelBot!");
                break;

            case "You can view cities like:":
                cityService.getAll().forEach(c -> sendMessages(chatId, String.valueOf(c)));
                break;

            case "Do you need help?":
                sendMessages(chatId, "Write to me: Vladsinitsa23@gmail.com");
                break;

            default:
                Optional<CityEntity> city = Optional.ofNullable(cityService.findByName(inMessage));
                if (city.isPresent()) {
                    sendMessages(chatId, String.valueOf(city.get()));
                } else {
                    sendMessages(chatId, "There is no information about the city: " + inMessage);
                }
                break;
        }
    }

    private void sendMessages(final String chatId, final String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        setButtons(sendMessage);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Error during sending message.", e);
        }
    }

    private void setButtons(final SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("Cities"));
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add(new KeyboardButton("Help"));

        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
    }

    @PostConstruct
    public void registryBot() {
        try {
            telegramBotsApi.registerBot(this);
        } catch (TelegramApiRequestException e) {
            log.error("Error occurred during bot registration.", e);
        }
    }
}
