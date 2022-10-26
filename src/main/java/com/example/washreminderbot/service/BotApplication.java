package com.example.washreminderbot.service;

import com.example.washreminderbot.service.comands.*;

public class BotApplication {
    WeatherService myWeatherService;
    protected Command Initialisation;
    protected CanHaveList HelpCommand;
    protected Command ShallWashNow;
    protected Command SetPeriod;
    protected Command WhenToRemind;
    public static Command[] commandList;

    BotApplication() {
        // todo переделать в список команд, которые принимаем извне
        WhenToRemind = new WhenToRemind();
        myWeatherService = new WeatherService();
        Initialisation = new Initialisation();
        ShallWashNow = new ShallWashNow(myWeatherService);
        SetPeriod = new SetPeriod();
        HelpCommand = new HelpCommand();
        commandList = new Command[]{Initialisation, HelpCommand, ShallWashNow};
        HelpCommand.setList(commandList);
    }

    public String commandProcessor(String inputString, String nameOfUser) {
        for (var i : commandList) {
            if (i.isTriggered(inputString)) {
                if (i instanceof CanHaveNameOfUser) {
                    CanHaveNameOfUser initializationName = (CanHaveNameOfUser) i;
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
