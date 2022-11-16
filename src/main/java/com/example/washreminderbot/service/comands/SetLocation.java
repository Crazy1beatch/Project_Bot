package com.example.washreminderbot.service.comands;

import com.example.washreminderbot.service.dataBase.DataBase;

public class SetLocation implements Command, CanWorkWithDataBase {
    public SetLocation(DataBase dataBase){
        this.dataBase = dataBase;
    }
    private final String helpResponse = """
            /location - поменять/установить местоположение""";

    @Override
    public String getHelp() {
        return helpResponse;
    }

    @Override
    public boolean isTriggered(String commandName) {
        return commandName.equals("/location");
    }

    @Override
    public String Execute() {
        dataBase.changeCoordinateStatus(chatID, dataTable);
        return "Теперь кинь мне свою геопозицию)";
    }

    DataBase dataBase;
    long chatID;
    String dataTable;
    @Override
    public void setDataKeys(long chatID, String dataTable) {
        this.chatID = chatID;
        this.dataTable = dataTable;
    }
}
