package com.example.washreminderbot.service;

import com.example.washreminderbot.service.comands.*;
import com.example.washreminderbot.service.dataBase.DataBase;
import com.example.washreminderbot.weathersearch.GetWeatherFromOpenWeather;

public class BotApplication {
    WeatherService myWeatherService;
    protected Command Initialisation;
    protected CanHaveList HelpCommand;
    protected Command ShallWashNow;
    protected Command SetPeriod;
    protected Command WhenToRemind;
    protected Command SetLocation;
    public static Command[] commandList;
    protected DataBase dataBase;

    BotApplication(DataBase dataBase) {
        // todo переделать в список команд, которые принимаем извне
        this.dataBase = dataBase;
        WhenToRemind = new WhenToRemind();
        myWeatherService = new WeatherService(new GetWeatherFromOpenWeather());
        Initialisation = new Initialisation(dataBase);
        ShallWashNow = new ShallWashNow(myWeatherService, dataBase);
        SetPeriod = new SetPeriod();
        HelpCommand = new HelpCommand();
        SetLocation = new SetLocation(dataBase);
        commandList = new Command[]{Initialisation, HelpCommand, ShallWashNow, SetLocation};
        HelpCommand.setList(commandList);
    }

    public String commandProcessor(String inputString, String nameOfUser, long chatID) {
        for (var i : commandList) {
            if (i.isTriggered(inputString)) {
                if (i instanceof CanWorkWithDataBase data)
                    data.setDataKeys(chatID, "usersdata");
                if (i instanceof CanHaveNameOfUser initializationName) {
                    initializationName.setNameOfUser(nameOfUser);
                }
                return i.Execute();
            }
        }
        return defaultAnswer();
    }

    private final String defaultAnswer = "Извини, я просто глупый бот";

    public String defaultAnswer() {
        return defaultAnswer;
    }

}
