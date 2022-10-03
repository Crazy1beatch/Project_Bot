package com.example.washreminderbot;

import com.example.washreminderbot.service.TelegramBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            telegramBotsApi.registerBot(new TelegramBot("WashReminderBOT",
                    "5673942555:AAFl6zGTXiHt9h1FTNkEHwL-Q0fWx3PPJ08"));
        } catch (TelegramApiException e) {

        }
    }
}
