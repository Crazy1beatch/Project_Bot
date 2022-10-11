package com.example.washreminderbot.service;

import com.example.washreminderbot.service.comands.*;

public class BotApplication {
    BotLogic myBotLogic;
    protected Command Initialisation;
    protected Command HelpCommand;
    protected Command ShallWashNow;
    protected Command SetPeriod;
    protected Command WhenToRemind;
    public static Command[] commandList;

    BotApplication() {
        WhenToRemind = new WhenToRemind();
        myBotLogic = new BotLogic();
        Initialisation = new Initialisation();
        ShallWashNow = new ShallWashNow(myBotLogic);
        SetPeriod = new SetPeriod();
        HelpCommand = new HelpCommand();
        commandList = new Command[]{Initialisation, HelpCommand, ShallWashNow, SetPeriod, WhenToRemind};
        if (HelpCommand instanceof CanHaveList) {
            CanHaveList helpList = (CanHaveList) HelpCommand;
            helpList.setList(commandList);
        }
    }

    public String commandProcessor(String inputString, String nameOfUser) {
        boolean isFlag = false;
        Command tmpCommand = null;
        for (var i : commandList) {
            if (i.isTriggered(inputString)) {
                isFlag = true;
                tmpCommand = i;
            }
        }
        if (isFlag) {
            return tmpCommand.Execute(nameOfUser);
        } else {
            return defaultAnswer();
        }
    }

    private final String defaultAnswer = "Извини, я просто глупый бот";

    public String defaultAnswer() {
        return defaultAnswer;
    }

}
