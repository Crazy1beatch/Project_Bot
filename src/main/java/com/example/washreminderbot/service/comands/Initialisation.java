package com.example.washreminderbot.service.comands;


import com.example.washreminderbot.service.dataBase.DataBase;

public class Initialisation implements Command, CanHaveNameOfUser, CanWorkWithDataBase{
    private final String helpAnswer = """
            /start - начать работу с ботом или сбросить настройки""";
    public String getHelp() {
        return helpAnswer;
    }

    public boolean isTriggered(String nameOfCommand) {
        return nameOfCommand.equals("/start");
    }

    private String nameOfUser = "dsf";
    private long chatID;
    private String dataTable;
    public String Execute() {
        dataBase.addNewUser(chatID, dataTable);
        return "Привет, " + nameOfUser + ", я твой персональный бот-напоминалка)\n" +
                "Со мной твоя машинка всегда будет чистой!)";
    }

    @Override
    public void setNameOfUser(String nameOfUser) {
        this.nameOfUser = nameOfUser;
    }

    protected DataBase dataBase;
    public Initialisation(DataBase dataBase){
        this.dataBase = dataBase;
    }

    @Override
    public void setDataKeys(long chatID, String dataTable) {
        this.chatID = chatID;
        this.dataTable = dataTable;
    }
}
