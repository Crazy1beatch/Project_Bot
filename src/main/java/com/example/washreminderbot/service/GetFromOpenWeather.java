package com.example.washreminderbot.service;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetFromOpenWeather {
    public JSONObject getResponse(String url) {
        var response = new StringBuilder();
        try {
            var obj = new URL(url);
            var connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");
            var in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            if (response.charAt(0) == '[') {
                var JSONArrayItem = new JSONArray(response.toString());
                return (JSONObject) JSONArrayItem.get(0);
            }
            return new JSONObject(response.toString());
        } catch (IOException e) {
            return null;
        }

    }
}
