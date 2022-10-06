package com.example.washreminderbot.service;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot extends TelegramLongPollingBot {
    protected BotApplication myApplication;
    private final String name;
    private final String apikey;

    public TelegramBot(String name, String apikey) {
        this.name = name;
        this.apikey = apikey;
        this.myApplication = new BotApplication();
    }

    public String getBotUsername() {
        return name;
    }

    public String getBotToken() {
        return apikey;
    }

    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            var messageText = update.getMessage().getText();
            var chatId = update.getMessage().getChatId();
            sendMessage(chatId, myApplication.commandProcessor(messageText, update.getMessage().getChat().getFirstName()));
//                case "/when" -> sendMessage(chatId, "jfj");
        }
    }

    private void sendMessage(long chatId, String textToSend) {
        var message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
