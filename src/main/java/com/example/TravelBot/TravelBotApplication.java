package com.example.TravelBot;

import io.swagger.annotations.Api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;

@EnableSwagger2
@SpringBootApplication
public class TravelBotApplication {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }
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

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any()).build().pathMapping("/")
                .apiInfo(apiInfo()).useDefaultResponseMessages(false);
    }

    @Bean
    public ApiInfo apiInfo() {
        final ApiInfoBuilder builder = new ApiInfoBuilder();
        builder.title("TravelBot API through Swagger UI").version("1.0").license("(C) TravelBot Copyright")
                .description("List of all the APIs of TravelBot App through Swagger UI");
        return builder.build();
    }
}
