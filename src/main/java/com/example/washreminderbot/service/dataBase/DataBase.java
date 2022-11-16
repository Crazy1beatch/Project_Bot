package com.example.washreminderbot.service.dataBase;

public interface DataBase {
    boolean getCoordinateStatus(long chatID, String tableName);
    void changeCoordinateStatus(long chatID, String tableName);
    void setCity(long chatID, String tableName, String city);
    String getCity(long chatID, String tableName);
    void addNewUser(long chatID, String tableName);
}
