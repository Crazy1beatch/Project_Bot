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
                    "5673942555:AAGzrZ-HyurWlTvvLf1rebaK2uT3TFRcTA4"));
            // todo новый не коммитить (я уже заменил)
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
