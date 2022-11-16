package com.example.washreminderbot;

import com.example.washreminderbot.service.TelegramBot;
import com.example.washreminderbot.service.dataBase.DataBasePostgreSQL;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {

    public static void main(String[] args) throws TelegramApiException {

        // Di.configure();
        var dataBase = new DataBasePostgreSQL(
                System.getenv("DATABASE_USER"),
                System.getenv("DATABASE_PASS"),
                System.getenv("DATABASE_URL")
        );
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            telegramBotsApi.registerBot(
                    new TelegramBot(System.getenv("BOT_NAME"),
                            System.getenv("BOT_TOKEN"), dataBase)
            );
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    // configure ( ) {
    /*
        CanHaveWeather -> GetWeatherFromOpenWeather
        Command[] ->

        Mock.CallTo<CanHaveWeather>(x => x....).Return(42);
     */

    // Di-container


}
