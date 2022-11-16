package com.example.washreminderbot.service;

import com.example.washreminderbot.getCityByCoordinates.CanGetCityByCoordinates;
import com.example.washreminderbot.getCityByCoordinates.GetCityByCoordinates;
import com.example.washreminderbot.service.dataBase.DataBase;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot extends TelegramLongPollingBot {
    private final String name;
    private final String apikey;
    protected BotApplication myApplication;
    protected DataBase dataBase;
    protected CanGetCityByCoordinates GetCityByCoordinates;

    public TelegramBot(String name, String apikey, DataBase dataBase) {
        this.name = name;
        this.apikey = apikey;
        this.dataBase = dataBase;
        this.myApplication = new BotApplication(dataBase);
        GetCityByCoordinates = new GetCityByCoordinates();
    }

    public String getBotUsername() {
        return name;
    }

    public String getBotToken() {
        return apikey;
    }

    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            var chatId = update.getMessage().getChatId();
            if (update.getMessage().hasText() && !dataBase.getCoordinateStatus(chatId, "usersdata")) {
                var messageText = update.getMessage().getText();
                sendMessage(chatId, myApplication.commandProcessor(messageText, update.getMessage().getChat().getFirstName(), chatId));
            } else if (update.getMessage().hasLocation() && dataBase.getCoordinateStatus(chatId, "usersdata")){
                double lat = update.getMessage().getLocation().getLatitude();
                double lon = update.getMessage().getLocation().getLongitude();
                var city = GetCityByCoordinates.getCity(lat, lon);
                dataBase.setCity(chatId, "usersdata", city);
                sendMessage(chatId, "Хмм \uD83E\uDD78, кажется твой город - " + city);
                sendMessage(chatId, "Супер, теперь я знаю где ты)");
            }
            else if(dataBase.getCoordinateStatus(chatId, "usersdata")){
                sendMessage(chatId, "Я всё ещё жду твоей геолокации -_-(");
            }
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
