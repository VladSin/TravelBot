package com.example.TravelBot;

import com.example.TravelBot.config.BotConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.telegram.telegrambots.ApiContext;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.bots.DefaultBotOptions;

@Controller
@SpringBootApplication
@EnableScheduling
public class TravelBotApplication {

	@RequestMapping("/")
	@ResponseBody
	String home() {
		return "Hello World!";
	}

	public static void main(String[] args) {
		SpringApplication.run(TravelBotApplication.class, args);

		ApiContextInitializer.init();

		DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);

		BotConfig botConfig = new BotConfig(botOptions);

		TelegramBotsApi botsApi = new TelegramBotsApi();
		try {
			botsApi.registerBot(botConfig);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
