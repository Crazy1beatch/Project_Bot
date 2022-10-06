package com.example.washreminderbot.service.comands;

public interface Command {
    public String getHelp();
    public boolean isTriggered(String nameOfCommand);
    public String Execute(String nameOfUser);
    public void setSettings(Command[] commandList);
}
