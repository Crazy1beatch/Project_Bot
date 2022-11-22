package com.example.washreminderbot.service.dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBasePostgreSQL implements DataBase {
    private static Connection connection;
    private static String DATABASE_USER;
    private static String DATABASE_PASSWORD;
    private static String DATABASE_URL;

    public DataBasePostgreSQL(String DATABASE_USER, String DATABASE_PASSWORD, String DATABASE_URL) {
        DataBasePostgreSQL.DATABASE_USER = DATABASE_USER;
        DataBasePostgreSQL.DATABASE_PASSWORD = DATABASE_PASSWORD;
        DataBasePostgreSQL.DATABASE_URL = DATABASE_URL;

    }

    @Override
    public boolean getCoordinateStatus(long chatID, String tableName) {
        try {
            try {
                connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("DataBaseConnection FAIL");
            }
            var statement = connection.createStatement();
            var response = String.format("SELECT need_coordinates from %s where chat_id = %d", tableName, chatID);
            var resultSet = statement.executeQuery(response);
            resultSet.next();
            return resultSet.getBoolean(1);

        } catch (SQLException e) {
            this.addNewUser(chatID, tableName);
        }
        return true;
    }

    @Override
    public void changeCoordinateStatus(long chatID, String tableName) {
        try {
            var statement = connection.createStatement();
            var response = String.format("INSERT INTO %s (chat_id, need_coordinates, city) VALUES (%d, %b, '')\n" +
                    "ON CONFLICT (chat_id)\n" +
                    "DO UPDATE SET chat_id = excluded.chat_id, need_coordinates = excluded.need_coordinates, " +
                    "city = excluded.city;", tableName, chatID, !this.getCoordinateStatus(chatID, tableName));
            statement.executeUpdate(response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setCity(long chatID, String tableName, String city) {
        try {
            var statement = connection.createStatement();
            var response = String.format("INSERT INTO %s (chat_id, need_coordinates, city) " +
                    "VALUES (%d, false, '%s')\n" +
                    "ON CONFLICT (chat_id)\n" +
                    "DO UPDATE SET chat_id = excluded.chat_id, need_coordinates = excluded.need_coordinates, " +
                    "city = excluded.city;", tableName, chatID, city);
            statement.executeUpdate(response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getCity(long chatID, String tableName) {
        try {
            var statement = connection.createStatement();
            var response = String.format("SELECT city from %s where chat_id = %d", tableName, chatID);
            var resultSet = statement.executeQuery(response);
            resultSet.next();
            return resultSet.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addNewUser(long chatID, String tableName) {
        var response = String.format("INSERT INTO %s (chat_id, need_coordinates, city) VALUES (%d, false, '')\n" +
                "ON CONFLICT (chat_id)\n" +
                "DO UPDATE SET chat_id = excluded.chat_id, need_coordinates = excluded.need_coordinates, " +
                "city = excluded.city;", tableName, chatID);
        try {
            var statement = connection.createStatement();
            statement.executeUpdate(response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
