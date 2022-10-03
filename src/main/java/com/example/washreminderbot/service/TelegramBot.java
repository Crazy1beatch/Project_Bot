package com.example.washreminderbot.service;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot extends TelegramLongPollingBot {
    BotLogic myBotLogic = new BotLogic();
    final String name;
    final String apikey;

    public TelegramBot(String name, String apikey) {
        this.name = name;
        this.apikey = apikey;
    }

    public String getBotUsername() {
        return name;
    }

    public String getBotToken() {
        return apikey;
    }

    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            switch (messageText) {
                case "/start" ->
                        sendMessage(chatId, myBotLogic.initialisation(update.getMessage().getChat().getFirstName()));
                case "/help" -> sendMessage(chatId, myBotLogic.listOfHelp());
                case "/now" -> sendMessage(chatId, myBotLogic.shallIWashNow());
                case "/when" -> sendMessage(chatId, "jfj");
                case "/period" -> sendMessage(chatId, "d");
                default -> sendMessage(chatId, myBotLogic.defaultStuff());
            }
        }
    }

    private void sendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
