package com.example.washreminderbot.service.comands;

public interface CanWorkWithDataBase extends Command{
    void setDataKeys(long chatID, String dataTable);
}
