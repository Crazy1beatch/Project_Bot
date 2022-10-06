package com.example.washreminderbot.service.comands;

import com.example.washreminderbot.service.BotApplication;

import java.util.Objects;

public class Initialisation implements Command{
    private final String helpAnswer = """
            /start - начать работу с ботом или сбросить настройки""";
    public String getHelp() {
        return helpAnswer;
    }

    public boolean isTriggered(String nameOfCommand) {
        return nameOfCommand.equals("/start");
    }

    public String Execute(String nameOfUser) {
        return "Привет, " + nameOfUser + ", я твой персональный бот-напоминалка)\n" +
                "Со мной твоя машинка всегда будет чистой!)";
    }

    @Override
    public void setSettings(Command[] commandList) {

    }
}
