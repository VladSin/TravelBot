package com.example.TravelBot.config;

import com.example.TravelBot.entity.CityEntity;
import com.example.TravelBot.service.CityService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Slf4j
@Component
public class BotConfig extends TelegramLongPollingBot {

    private final CityService cityService;

    @Value("${telegrambot.botName}")
    private String botName;

    @Value("${telegrambot.botToken}")
    private String botToken;

    @Autowired
    public BotConfig(DefaultBotOptions options, CityService cityService) {
        super(options);
        this.cityService = cityService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            if (update.getMessage().hasText()) {
                log.info(update.toString());
                sendMsg(update.getMessage().getChatId(),
                        update.getMessage().getText(),
                        update.getMessage().getMessageId());
            }
        } else if (update.hasCallbackQuery()) {
            log.info("Callback {}", update.toString());
            EditMessageText newMessage = new EditMessageText()
                    .setChatId(update.getCallbackQuery().getMessage().getChatId())
                    .setMessageId(update.getCallbackQuery().getMessage().getMessageId())
                    .setText(update.getCallbackQuery().getData());
            try {
                execute(newMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            sendMsg(update.getCallbackQuery().getMessage().getChatId(),
                    update.getCallbackQuery().getData(),
                    update.getCallbackQuery().getMessage().getMessageId());
        }
    }

    private void sendMsg(Long chatId, String message, Integer messageId) {

        SendMessage sendMessage;
        String send = "";

        if (message.equals("/start") || message.equals("/Start")) {
            send = "Welcome!!! \nI'll help you find the best places to visit! \nEnter city name:";
            sendMessage = createMessage(send, chatId, messageId);
        } else {
            CityEntity cityEntity = cityService.findByName(message);
            if (cityEntity.getInfo() != null) {
                if (!cityEntity.getInfo().isEmpty()) {
                    send = String.join(".\n", cityEntity.getInfo());
                } else {
                    send = "I have no information about this city... Sorry!((";
                }
                sendMessage = createMessage(send, chatId, messageId);
            } else {
                List<CityEntity> citiesMeaning = cityService.getAll();
                if (!citiesMeaning.isEmpty()) {
                    List<String> citiesName = citiesMeaning.stream()
                            .map(CityEntity::getName).collect(Collectors.toList());
                    send = "I don't know this city... Sorry!(( \nChoose another:";
                    sendMessage = createMessage(send, chatId, messageId, setInline(citiesName));
                } else {
                    send = "I don't know this city... Sorry!((";
                    sendMessage = createMessage(send, chatId, messageId);
                }
            }
        }

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Message not sent. Update message {}", message);
            e.printStackTrace();
        }
    }

    private SendMessage createMessage(String send, Long chatId, Integer messageId, InlineKeyboardMarkup setInline) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setReplyMarkup(setInline);
        sendMessage.setText(send);

        return sendMessage;
    }

    private SendMessage createMessage(String send, Long chatId, Integer messageId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setReplyToMessageId(messageId);
        sendMessage.setChatId(chatId);
        sendMessage.setText(send);
        return sendMessage;
    }


    private InlineKeyboardMarkup setInline(List<String> cities) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        for (String city : cities) {
            List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
            keyboardButtonsRow.add(new InlineKeyboardButton().setText(city).setCallbackData(city));
            rowList.add(keyboardButtonsRow);
        }

        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }


    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}
