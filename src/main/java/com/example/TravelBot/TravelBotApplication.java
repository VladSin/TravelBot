package com.example.TravelBot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class TravelBotApplication {

//    @RequestMapping("/")
//    @ResponseBody
//    String home() {
//        return "Hello World!";
//    }
//
//    @Bean
//    public TelegramBotsApi telegramBotsApi() {
//        return new TelegramBotsApi();
//    }
//
//    @PostConstruct
//    public void init() {
//        ApiContextInitializer.init();
//    }

    public static void main(String[] args) {
        SpringApplication.run(TravelBotApplication.class, args);
    }
}
