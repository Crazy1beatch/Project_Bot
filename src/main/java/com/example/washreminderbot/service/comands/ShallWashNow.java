package com.example.washreminderbot.service.comands;

import com.example.washreminderbot.service.WeatherService;
import com.example.washreminderbot.service.dataBase.DataBase;

public class ShallWashNow implements Command, CanWorkWithDataBase {
    private final String helpAnswer = """
            /now - спросить, стоит ли сейчас мыть машину""";
    private final String answerCaseYes = "Да, сегодня самый день, чтобы поехать на мойку <3";
    private final String answerCaseNo = "Нет, лучше сегодня не делать этого -_- (";
    private final String answerCaseError = "Я не знаю, где ты(\nПопробуй /location, чтобы задать город";
    protected WeatherService myWeatherService;
    private DataBase dataBase;
    private long chatID;
    private String dataTable;


    public ShallWashNow(WeatherService myWeatherService, DataBase dataBase) {
        this.myWeatherService = myWeatherService;
        this.dataBase = dataBase;
    }

    @Override
    public String getHelp() {
        return helpAnswer;
    }

    @Override
    public boolean isTriggered(String nameOfCommand) {
        return nameOfCommand.equals("/now");
    }

    @Override
    public String Execute() {
        var resultOfCheck =
                myWeatherService.isWeatherGood(dataBase.getCity(chatID, dataTable));

        return resultOfCheck.getErrors() ?
                answerCaseError : resultOfCheck.getResult() ?
                answerCaseYes : answerCaseNo;
    }

    @Override
    public void setDataKeys(long chatID, String dataTable) {
        this.chatID = chatID;
        this.dataTable = dataTable;
    }
}
