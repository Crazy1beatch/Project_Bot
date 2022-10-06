package com.example.washreminderbot.service.comands;

public class SetPeriod implements Command{
    private final String helpAnswer = """
            /period - настроить период напоминаний""";
    @Override
    public String getHelp() {
        return helpAnswer;
    }

    @Override
    public boolean isTriggered(String nameOfCommand) {
        return nameOfCommand.equals("/period");
    }

    @Override
    public String Execute(String nameOfUser) {
        return "Вас понял!";
    }

    @Override
    public void setSettings(Command[] commandList) {

    }
}
