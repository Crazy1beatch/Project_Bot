package com.example.washreminderbot.service.comands;

import com.example.washreminderbot.service.WeatherService;
import com.example.washreminderbot.weathersearch.CanHaveWeather;

public class ShallWashNow implements Command {
    private final String helpAnswer = """
            /now - спросить, стоит ли сейчас мыть машину""";
    private final String answerCaseYes = "Да, сегодня самый день, чтобы поехать на мойку <3";
    private final String answerCaseNo = "Нет, лучше сегодня не делать этого -_- (";
    private final String answerCaseError = "Я не знаю такого города(";

    protected WeatherService myWeatherService;
    CanHaveWeather weatherFromOpenWeather;

    public ShallWashNow(WeatherService myWeatherService) {
        this.myWeatherService = myWeatherService;
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
        var resultOfCheck = myWeatherService.isWeatherGood("Екатеринбург");

        return resultOfCheck.getErrors() ?
                answerCaseError : resultOfCheck.getResult() ?
                answerCaseYes : answerCaseNo;
    }
}
