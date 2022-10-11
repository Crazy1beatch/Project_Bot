package com.example.washreminderbot.service.comands;

import java.util.Objects;

public class WhenToRemind implements Command{
    private final String helpAnswer = """
            /when - Задать день и время, когда напомнить мыть машину""";
    @Override
    public String getHelp() {
        return helpAnswer;
    }

    @Override
    public boolean isTriggered(String nameOfCommand) {
        return nameOfCommand.equals("/when");
    }

    @Override
    public String Execute(String nameOfUser) {
        return "null";
    }
}
