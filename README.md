# Telegram Bot Application
## Данное Web-приложение предназначено для управления туристическим ботом

* Для запуска Вам необходимо лишь написать ему: http://t.me/TravelInformationBot
* Документация по приложению: https://travel-info-bot.herokuapp.com/swagger-ui.html
* По вопросам обращайтесь: Vladsititsa23@gmail.com или https://t.me/VladislavSinitsa

## Что реализовано:
*	Приложение размещено на платформе Heroky (https://www.heroku.com/)
*	Данные о городах хранятся на облачном сервисе Heroku Postgre SQL
*	Управление данными о городах производится через REST WebService
*	В случае необходимости, возможна быстрая реализация простого web-сайта для регистрации пользователя или администратора для управления и просмотра данных в базе (Для этого прописан REST для User)
*	Произведено тестирование service-методов, а также возможных запросов (REST) с помощью Insomnia (https://insomnia.rest/)

## Стек используемых технологий:
* Java 1.8
* Сборка осуществлялась на Maven
*	SpringBoot, SpringMVC, SpringData, Hibernate, Lombok
*	Базы данных: Heroku Postgre SQL, MySQL (для локального тестирования)
*	Swagger (для составления документации по REST)

## Функционал приложения:
* ### /start – Приветствие пользователя 
<p align="center"><img src="https://github.com/VladSin/TravelBot/blob/master/Gif%20Video%201.gif" width=200></p>

* ### /list – Список городов в базе данных 
<p align="center"><img src="https://github.com/VladSin/TravelBot/blob/master/Gif%20Video%202.gif" width=200></p>

* ### Производим выбор города из списка и получаем информацию о нем 
<p align="center"><img src="https://github.com/VladSin/TravelBot/blob/master/Gif%20Video%203.gif" width=200></p>

* ### /help – В случае возникновения проблем (почта разработчика) 
<p align="center"><img src="https://github.com/VladSin/TravelBot/blob/master/Gif%20Video%204.gif" width=200></p>

* ### Города нет в списке – это не проблема! 
<p align="center"><img src="https://github.com/VladSin/TravelBot/blob/master/Gif%20Video%205.gif" width=200></p>
